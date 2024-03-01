package com.gmahamed.search;

import java.util.ArrayList;
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
    //Queue and a set to hold all the nodes yet to be evaluated and already evaluated
    PriorityQueue<Node> openNodes;
    HashSet<Node> closedNodes;

    //The connectivity space of the nodes
    Node[][] space;
    Node startNode;
    Node goalNode;
    Node currentNode;

    //Constructor used when there are no blocked nodes
    public AStar(int spaceWidth, int spaceHeight, int startI, int startJ, int goalI, int goalJ) {
        this(spaceWidth, spaceHeight, startI, startJ, goalI, goalJ, new ArrayList<>());
    }

    //Constructor used when there are blocked nodes
    public AStar(int spaceWidth, int spaceHeight, int startI, int startJ, int goalI, int goalJ,
            List<Node> blockedNodes) {
        space = new Node[spaceWidth][spaceHeight];
        //Open nodes queue sorted by the value of F
        openNodes = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        //Set that holds nodes already evaluated
        closedNodes = new HashSet<Node>();
        startNode = new Node(startI, startJ);
        goalNode = new Node(goalI, goalJ);

        //Populate the entire space with nodes
        for (int i = 0; i < space.length; i++) {
            for (int j = 0; j < space[i].length; j++) {
                space[i][j] = new Node(i, j);
                space[i][j].setH(Math.abs(i - goalNode.i) + (j - goalNode.j));
            }
        }

        for (Node node : blockedNodes) {
            space[node.i][node.j].setBlocked(true);
        }

        startNode.setH(Math.abs(startNode.i - goalNode.i) + Math.abs(startNode.j - goalNode.j));
        openNodes.add(startNode);

        loop();
    }
    
    // Until goal node is found or there are no more nodes to expand, expand neighbour and add to openNodes.
    private void loop() {
        while (openNodes.size() > 0) {

            //remove first element of openNodes (The lowest F Cost), set it to currentNode and add to closedNodes
            currentNode = openNodes.poll();
            closedNodes.add(currentNode);

            //If the current node being evaluated is the goal node, find the solution path
            if (currentNode.equals(goalNode)) {
                for (Node node : getShortestPath(currentNode)) {
                    System.out.println(node);
                }
                break;
            }

            //Evaluate all nodes from list of neighbour nodes
            for (Node neighbour : expandNeighbours(currentNode)) {
                if (!closedNodes.contains(neighbour)) {
                    int gCostToNeighbour = currentNode.getG() + 1;
                    //no comparison between g nodes since horizontal and vertical movements are equal
                    if (!openNodes.contains(neighbour)) {
                        neighbour.setG(gCostToNeighbour);
                        openNodes.add(neighbour);
                        neighbour.setParent(currentNode);
                    }
                }
            }
        }

        if (openNodes.size() == 0) {
            System.out.println("No Solution");
        }
    }

    // Finds all neighbouring nodes around the node given, making sure that the neighbour node exists and not blocked.
    private List<Node> expandNeighbours(Node node){
        List<Node> neighbours = new ArrayList<>();
        int[][] directions = new int[][]{
            {1, 0}, //down
            {-1, 0}, //up
            {0, -1}, //left
            {0, 1}, //right
        };

        for (int[] dir : directions) {
            //The location of neighbour node
            int xi = node.i + dir[0];
            int xj = node.j + dir[1];

            //checks if neighbour is in the space and not blocked, if so then create node and add to list.
            if(xi >= 0 && xi < space.length && xj >= 0 && xj < space[0].length && !space[xi][xj].isBlocked()){
                Node neighbour = space[xi][xj];
                neighbours.add(neighbour);
            }

        }
        return neighbours;
    }

    
    /**
     * 
     * Uses the Node class getSolution method 
     * 
     * @param goalNode The node to start finding parent node until the start node. 
     * This will be reversed to find the solution path.
     * @return List<Node> that holds all the nodes until the goal node.
     */
    public List<Node> getShortestPath(Node goalNode) {
        return goalNode.getSolution();
    }

    public static void main(String[] args) {
        List<Node> blockedNodes = new ArrayList<>();
        blockedNodes.add(new Node(0, 1));
        blockedNodes.add(new Node(1, 1)); 
        blockedNodes.add(new Node(2, 1)); 
        new AStar(3, 3, 0, 0, 2, 2, blockedNodes);
    }
}