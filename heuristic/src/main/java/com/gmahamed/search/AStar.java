package com.gmahamed.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author Khalid Mahamed
 * 
 *         An implementation of a domain specific optimal pathfinding algorithm A*.
 *         The algorithm works by taking the parameters of the connectivity space, 
 *         the start state, the goal state, list of blocked nodes and list of items.
 *         The algortihm is designed to find the most optimal path to the landmarks laid out,
 *         and actions to take to reach the goal state. 
 *         
 * 
*/

public class AStar {
    PriorityQueue<State> openStates;
    HashSet<State> closedStates;
    List<State> itemList;
    AStarGUI gui;

    //The connectivity space of the nodes
    Node[][] space;
    State startState;
    State goalState;
    State currentState;

    /*
     * Public constructor that runs the A* algorithm with parameters only for the connectivity space,
     * start state and goal state.
     * 
     * @param spaceWidth, spaceHeight, startState and goalState. 
     * Parameters used to initialise the A* search algorithm.
    */

    public AStar(int spaceWidth, int spaceHeight, State startState, State goalState) {
        // No blocked nodes and no items
        this(spaceWidth, spaceHeight, startState, goalState, new ArrayList<>(), new ArrayList<>());
    }

    /*
     * Public constructor that runs the A* algorithm with parameters only for the connectivity space,
     * start state, goal state and blocked nodes.
     * 
     * @param spaceWidth, spaceHeight, startState, goalState and blockedNodes. 
     * Parameters used to initialise the A* search algorithm.
    */
    public AStar(int spaceWidth, int spaceHeight, State startState, State goalState, List<Node> blockedNodes){
        // Blocked nodes but no items
        this(spaceWidth, spaceHeight, startState, goalState, blockedNodes, new ArrayList<>());
    }

    /*
     * Public constructor that runs the A* algorithm with parameters for the connectivity space,
     * start state, goal state, blocked nodes and items list.
     * 
     * @param spaceWidth, spaceHeight, startState, goalState, blockedNodes and items. 
     * Parameters used to initialise the A* search algorithm.
    */
    public AStar(int spaceWidth, int spaceHeight, State startState, State goalState, List<Node> blockedNodes, List<State> items) {
        space = new Node[spaceWidth][spaceHeight];
        //Open nodes queue sorted by the value of F
        openStates = new PriorityQueue<>(Comparator.comparingInt(State::getF));
        //Set that holds nodes already evaluated
        closedStates = new HashSet<State>();
        this.startState = startState;
        this.goalState = goalState;
        //goalState added to itemList as that is used as the main way to pre compute heuristic cost of nodes
        itemList = items;
        //gui
        gui = new AStarGUI(spaceWidth, spaceHeight, startState, goalState, blockedNodes, itemList);

        //Populate the entire space with nodes
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[i].length; j++) {
                space[i][j] = new Node(i, j);
            }
        }

        for (Node node : blockedNodes) {
            space[node.i][node.j].setBlocked(true);
        }

        startState.getNode().setH(calculateHCost(startState, itemList));
        openStates.add(startState);

        loop();
    }

    // Until goal State is found or there are no more States to expand, expand neighbour and add to openStates.
    private void loop() {
        boolean solutionFound = false;
        //Stores the time when states starts to expand
        long startTime = System.currentTimeMillis();
        int nodesExpanded = 0;
        int itemCount = itemList.size();
    
        while (!openStates.isEmpty()) {
            currentState = openStates.poll();
            gui.updateGrid(currentState);
            closedStates.add(currentState);
            nodesExpanded++;

            //if solution is found
            if (currentState.getNode().equals(goalState.getNode()) && itemList.size() == 0) {
                //Stores the time when solution is found
                long endTime = System.currentTimeMillis();
                //Time solution found - time A* starts executing
                long totalTime = endTime - startTime;
                System.out.println("Solution found in " + totalTime + " milliseconds.");
                //if startState and goalState are the same with no landmarks
                if (currentState.getPreviousState() == null) {
                    System.out.println("Start state is the goal state: " + currentState.getNode());
                }
                for (String action : reconstructActions(currentState)) {
                    System.out.println(action);
                }
                solutionFound = true;
                break;
            }

            //Clears closedStates and openStates after landmark is reached
            if (itemCount != itemList.size()) {
                closedStates.clear();
                openStates.clear();
                itemCount = itemList.size();
            }
    
            List<State> successors = generateSuccessors(currentState);
            for (State successor : successors) {
                //Makes sure this doesnt expand unnecessary states
                if (!closedStates.contains(successor) && !openStates.contains(successor)) {
                    int gCostToNeighbour = currentState.getNode().getG() + 1;
                    successor.getNode().setG(gCostToNeighbour);
                    successor.setPreviousState(currentState);
                    openStates.add(successor);
                }
            }
        }
    
        if (!solutionFound) {
            System.out.println("No Solution");
        } else {
            System.out.println("Nodes expanded: " + nodesExpanded);
        }
    }

    /** 
     * This method traverses backward from the Goal state to the Start state,
     * generating a list of actions in string format that needs to be taken to reach the Goal.
     * 
     * @param goal State where the reconstruction starts.
     * @return List<String> that contains reversed list of actions taken in string format.
     */
    public List<String> reconstructActions(State goal) {
        List<String> actions = new ArrayList<>();
        State curr = goal;

        //Until the the traversal backwards reaches the start state
        while (curr != null && curr.getPreviousState() != null) {
            gui.reconstructGrid(curr);
            if(!curr.getNode().equals(curr.getPreviousState().getNode())){
                ActionType move = ActionType.MOVE;
                actions.add(new String(move.toString() + " " + curr.getPreviousState().getNode().toString() + " -> " + curr.getNode().toString()));
            }
            else if (curr.hasItem(Item.KEY) && curr.getPreviousState() != null && !curr.getPreviousState().hasItem(Item.KEY)){
                ActionType pick = ActionType.PICK_UP;
                actions.add(new String(pick.toString() + " " + Item.KEY.toString()));
            }
            else if (curr.hasItem(Item.TROPHY) && curr.getPreviousState() != null && !curr.getPreviousState().hasItem(Item.KEY)){
                ActionType pick = ActionType.PICK_UP;
                actions.add(new String(pick.toString() + " " + Item.TROPHY.toString()));
            }
            else if (curr.hasItem(Item.LADDER) && curr.getPreviousState() != null && !curr.getPreviousState().hasItem(Item.KEY)){
                ActionType pick = ActionType.PICK_UP;
                actions.add(new String(pick.toString() + " " + Item.LADDER.toString()));
            }
            curr = curr.getPreviousState();
        }
        Collections.reverse(actions);
        return actions;
    }

    private List<State> generateSuccessors(State state) {
        List<State> successors = new ArrayList<>();

        for (ActionType action : ActionType.values()){
            if(actionApplicable(action, state)){
                //if the action is applicable, apply action to generate successor states
                List <State> successorStates = applyAction(action, state);
                for(State successorState : successorStates){
                    if (successorState != null) {
                        successorState.getNode().setH(calculateHCost(successorState, itemList));
                        //if action is pick up, prioritise and return that state alone
                        if(action == ActionType.PICK_UP){
                            List<State> pickUpSuccessors = new ArrayList<>();
                            pickUpSuccessors.add(successorState);
                            return pickUpSuccessors;
                        }
                        else{
                            successors.add(successorState);
                        }
                    }
                }
            }
        }
        return successors;
    }

    private List<State> applyAction(ActionType action, State state) {
        switch (action) {
            case MOVE:
                List<State> neighbours = expandNeighbours(state);
                return neighbours;
    
            case PICK_UP:
                List<State> carriedItems = new ArrayList<>();
                Iterator<State> iterator = itemList.iterator();
                while (iterator.hasNext()) {
                    State s = iterator.next();
                    // Check if the current state is on the same node as any item and the item is not carried
                    for (Item item : Item.values()) {
                        if (state.getNode().equals(s.getItemNode(item)) && !s.isCarried()) {
                            // Use iterator to safely remove elements instead of itemList.remove(item)
                            iterator.remove();
                            State infoState = new State(Entity.EXPLORER, state.getNode());
                            infoState.getNode().setG(state.getNode().getG());
                            infoState.store(item);
                            carriedItems.add(infoState);
                        }
                    }
                }
                return carriedItems;
    
            default:
                break;
        }
        return new ArrayList<>();
    }

    private boolean actionApplicable(ActionType action, State state) {
        switch (action) {
            case MOVE:
                if (!expandNeighbours(state).isEmpty())
                    return true;
    
            case PICK_UP:
                for (State s : itemList) {
                    for (Item item : Item.values()) {
                        Node itemNode = s.getItemNode(item);
                        if (itemNode != null && state.getNode() != null && state.getNode().equals(itemNode) && !s.isCarried())
                            return true;
                    }
                }
                return false;
    
            default:
                return false;
        }
    }
    
    

    private List<State> expandNeighbours(State state){
        List<State> neighbours = new ArrayList<>();
        int[][] directions = new int[][]{
            {1, 0}, //down
            {-1, 0}, //up
            {0, -1}, //left
            {0, 1}, //right
        };

        for (int[] dir : directions) {
            //The location of neighbour node
            int xi = state.getNode().i + dir[0];
            int xj = state.getNode().j + dir[1];

            //checks if neighbour is in the space and not blocked, if so then create node and add to list.
            if(xi >= 0 && xi < space.length && xj >= 0 && xj < space[0].length && !space[xi][xj].isBlocked()){
                Node neighbourNode = space[xi][xj];
                State neighbourState = new State(state.getEntity(), neighbourNode);
                neighbours.add(neighbourState);
            }
        }
        return neighbours;
    }

    //Pre calculates the heuristic cost based on landmarks and goalState
    private int calculateHCost(State tempState, List<State> landmarks) {
        if (landmarks.isEmpty()) {
            // If there are no landmarks, calculate distance to the goal state
            return calculateDistance(tempState.getNode(), goalState.getNode());
        }

        State closestLandmark = findClosestLandmark(tempState, landmarks);

        int hCost = calculateDistance(tempState.getNode(), closestLandmark.getItemNode(Item.KEY));

        // If the closest landmark is reached, remove it from the list
        if (tempState.getNode().equals(closestLandmark.getItemNode(Item.KEY))) {
            landmarks.remove(closestLandmark);
        }

        return hCost;
    }

    private State findClosestLandmark(State currentState, List<State> landmarks) {
        State closestLandmark = landmarks.get(0);
        int minDistance = calculateDistance(currentState.getNode(), closestLandmark.getItemNode(Item.KEY));
    
        for (int i = 1; i < landmarks.size(); i++) {
            int distance = calculateDistance(currentState.getNode(), landmarks.get(i).getItemNode(Item.KEY));
            if (distance < minDistance) {
                minDistance = distance;
                closestLandmark = landmarks.get(i);
            }
        }
    
        return closestLandmark;
    }

    // Calculates the Manhattan distance between two nodes.
    private int calculateDistance(Node node1, Node node2) {
        return Math.abs(node1.i - node2.i) + Math.abs(node1.j - node2.j);
    }


    public static void main(String[] args) {
        List<Node> blockedNodes = new ArrayList<>(){{
            add(new Node(0,1));
            add(new Node(1,1));
            add(new Node(2,1));
            add(new Node(3,1));
        }};
        List<State> items = new ArrayList<>(){{
            add(new State(Item.KEY, new Node(1, 2)));
            add(new State(Item.KEY, new Node(3, 2)));
        }};
        new AStar(5, 5, new State(Entity.EXPLORER, new Node(0, 0)), new State(Entity.EXPLORER, (new Node(0, 4))), blockedNodes, items);
    }
}