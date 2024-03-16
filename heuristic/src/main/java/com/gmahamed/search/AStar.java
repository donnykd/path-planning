package com.gmahamed.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
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

    // Until goal node is found or there are no more nodes to expand, expand neighbour and add to openNodes.
    private void loop() {
        while (openStates.size() > 0) {

            //remove first element of openNodes (The lowest F Cost), set it to currentNode and add to closedNodes
            currentState = openStates.poll();
            closedStates.add(currentState);

            //If the current node being evaluated is the goal node, find the solution path
            if (currentState.getNode().equals(goalState.getNode())) {
                //if goal state was the start state from the beginning 
                if(currentState.getPreviousState() == null){
                    System.out.println(currentState.getNode().toString());
                }
                for(String action : reconstructActions(currentState)){
                    System.out.println(action);
                }
                solutionFound = true;
                break;
            }

            //Evaluate all nodes from list of neighbour nodes
            List<State> successors = generateSuccessors(currentState);
            for (State successor : successors) {
                if (!closedStates.contains(successor)) {
                    int gCostToNeighbour = currentState.getNode().getG() + 1;
                    if(!openStates.contains(successor)){
                        successor.getNode().setG(gCostToNeighbour);
                        successor.setPreviousState(currentState);
                        openStates.add(successor);
                    }
                }
            }
        }

        if (!solutionFound) {
            System.out.println("No Solution");
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
            else if(curr.hasItem(Item.KEY) && !curr.getPreviousState().hasItem(Item.KEY)){
                ActionType pick = ActionType.PICK_UP;
                actions.add(new String(pick.toString() + " " + Item.KEY.toString()));
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
                        successors.add(successorState);
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
                List<State> validNeighbours = new ArrayList<>();

                for (State neighbour : neighbours) {
                    if (!closedStates.contains(neighbour)) {
                        validNeighbours.add(neighbour);
                    }
                }
                return validNeighbours;

            case PICK_UP:
                
                break;
        
            default:
                break;
        }
        return new ArrayList<>();
    }

    private boolean actionApplicable(ActionType action, State state) {
        switch (action) {
            case MOVE:
                if(!expandNeighbours(state).isEmpty())
                    return true;
    
            // case PICK_UP:
            //     for(State s : itemList){
            //         if(state.getNode().equals(s.getItemNode()) && !s.isCarried())
            //             return true;
            //     }
            //     break;
    
            default:
                return false;
        }
    }
    

    private List<State> expandNeighbours(State state){
        List<State> neighbours = new ArrayList<>();
        //check this ------------------------------------------------------------------------------------------------------------------------------------
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
            int distance = calculateDistance(tempState.getNode(), landmark.getNode());
            maxDistance = Math.max(maxDistance, distance);
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
        new AStar(5, 5, new State(Entity.EXPLORER, new Node(0, 0)), new State(Entity.EXPLORER, new Node(0, 3)), blockedNodes);
    }
}