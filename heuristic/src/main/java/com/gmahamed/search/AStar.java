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
 *         An implementation of the pathfinding algorithm A* created for the domain.
 *         
 *         
 * 
*/

public class AStar {
    private boolean solutionFound;
    PriorityQueue<State> openStates;
    HashSet<State> closedStates;
    List<State> itemList;

    //The connectivity space of the nodes
    Node[][] space;
    State startState;
    State goalState;
    State currentState;

    //Constructor used when there are no blocked nodes and no items
    public AStar(int spaceWidth, int spaceHeight, State startState, State goalState) {
        this(spaceWidth, spaceHeight, startState, goalState, new ArrayList<>(), new ArrayList<>());
    }

    //Constructor used when there are blocked nodes but no items
    public AStar(int spaceWidth, int spaceHeight, State startState, State goalState, List<Node> blockedNodes){
        this(spaceWidth, spaceHeight, startState, goalState, blockedNodes, new ArrayList<>());
    }

    //Constructor used when there are blocked nodes
    public AStar(int spaceWidth, int spaceHeight, State startState, State goalState, List<Node> blockedNodes, List<State> items) {
        space = new Node[spaceWidth][spaceHeight];
        //Open nodes queue sorted by the value of F
        openStates = new PriorityQueue<>(Comparator.comparingInt(State::getF));
        //Set that holds nodes already evaluated
        closedStates = new HashSet<State>();
        this.startState = startState;
        this.goalState = goalState;
        itemList = items;
        itemList.add(goalState);

        //Populate the entire space with nodes
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[i].length; j++) {
                space[i][j] = new Node(i, j);
                State tempState = new State(Entity.EXPLORER, space[i][j]);
                space[i][j].setH(calculateHCost(tempState, itemList));
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
        long startTime = System.currentTimeMillis();
        int nodesExpanded = 0;
        int itemCount = itemList.size() - 1;
    
        while (!openStates.isEmpty()) {
            currentState = openStates.poll();
            closedStates.add(currentState);
            nodesExpanded++;

            if (currentState.getNode().equals(goalState.getNode()) && itemList.size() == 1) {
                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                System.out.println("Solution found in " + totalTime + " milliseconds.");
                if (currentState.getPreviousState() == null) {
                    System.out.println("Start state is the goal state: " + currentState.getNode());
                }
                for (String action : reconstructActions(currentState)) {
                    System.out.println(action);
                }
                solutionFound = true;
                break;
            }

            for(Item item : Item.values()){
                if (currentState.hasItem(item) && itemCount != itemList.size() - 1) {
                    closedStates.clear();
                    openStates.clear();
                    itemCount = itemList.size() - 1;
                }
            }
    
            List<State> successors = generateSuccessors(currentState);
            for (State successor : successors) {
                if (!closedStates.contains(successor)) {
                    int gCostToNeighbour = currentState.getNode().getG() + 1;
                    if (!openStates.contains(successor)) {
                        successor.getNode().setG(gCostToNeighbour);
                        successor.setPreviousState(currentState);
                        openStates.add(successor);
                    }
                }
            }
        }
    
        if (!solutionFound) {
            System.out.println("No Solution");
        } else {
            System.out.println("Nodes expanded: " + nodesExpanded);
        }
    }

    public List<String> reconstructActions(State goal) {
        List<String> actions = new ArrayList<>();
        State curr = goal;

        while (curr != null && curr.getPreviousState() != null) {
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
                List <State> successorStates = applyAction(action, state);
                for(State successorState : successorStates){
                    if (successorState != null) {
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
                    for (Item item : Item.values()) {
                        if (state.getNode().equals(s.getItemNode(item)) && !s.isCarried()) {
                            // Use iterator to safely remove elements instead of itemList.remove(item)
                            iterator.remove();
                            s.setCarried(true);
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

    private int calculateHCost(State tempState, List<State> landmarks) {
        int maxDistance = 0;
    
        for(State landmark : landmarks){
            if(landmark.getEntity() == null){
                for(Item item : Item.values()){
                    Node node1 = tempState.getNode();
                    Node node2 = landmark.getItemNode(item);
                    if (node1 != null && node2 != null) { 
                        int distance = calculateDistance(node1, node2);
                        maxDistance = Math.max(maxDistance, distance);
                    }
                }
            }
            else{
                Node node1 = tempState.getNode();
                Node node2 = goalState.getNode();
                if (node1 != null && node2 != null) {
                    int distance = calculateDistance(node1, node2);
                    maxDistance = Math.max(maxDistance, distance);
                }
            }
        }
    
        return maxDistance;
    }

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
            add(new State(Item.TROPHY, new Node(3, 2)));
        }};
        new AStar(5, 5, new State(Entity.EXPLORER, new Node(0, 0)), new State(Entity.EXPLORER, (new Node(0, 0))), blockedNodes, items);
    }
}