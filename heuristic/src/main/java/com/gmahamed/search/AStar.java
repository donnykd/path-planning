package com.gmahamed.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class AStar {
    PriorityQueue<Node> openNodes;
    HashSet<Node> closedNodes;

    Node[][] space;
    Node startNode;
    Node goalNode;
    Node currentNode;

    public AStar(int spaceWidth, int spaceHeight, int startI, int startJ, int goalI, int goalJ){
        this(spaceWidth, spaceHeight, startI, startJ, goalI, goalJ, new ArrayList<>());
    }

    public AStar(int spaceWidth, int spaceHeight, int startI, int startJ, int goalI, int goalJ, List<Node> blockedNodes){
        space = new Node[spaceWidth][spaceHeight];
        openNodes = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        closedNodes = new HashSet<Node>();
        startNode = new Node(startI, startJ);
        goalNode = new Node(goalI, goalJ);

        for(int i=0; i < space.length; i++){
            for(int j = 0; j < space[i].length; j++){
                space[i][j] = new Node(i, j);
                space[i][j].setH(Math.abs(i - goalNode.i) + (j - goalNode.j));
            }
        }

        for(Node node : blockedNodes){
            space[node.i][node.j].setBlocked(true);
        }

        startNode.setH(Math.abs(startNode.i - goalNode.i) + Math.abs(startNode.j - goalNode.j));
        openNodes.add(startNode);

        while(openNodes.size() > 0){

            currentNode = openNodes.poll();
            closedNodes.add(currentNode);

            if(currentNode.equals(goalNode)){
                //find the solution
                for(Node node : getShortestPath(currentNode)){
                    System.out.println(node);
                }
                break;
            }

            for(Node neighbour : expandNeighbours(currentNode)){
                if(!closedNodes.contains(neighbour)){
                    int gCostToNeighbour = currentNode.getG() + 1;
                    if(!openNodes.contains(neighbour)){ //no comparison between g nodes since horizontal and vertical movements are equal
                        neighbour.setG(gCostToNeighbour);
                        openNodes.add(neighbour);
                        neighbour.setParent(currentNode);
                    }
                }
            }
        }

        if(openNodes.size() == 0){
            System.out.println("No Solution");
        }


    }

    private List<Node> expandNeighbours(Node node){
        List<Node> neighbours = new ArrayList<>();
        int[][] directions = new int[][]{
            {1, 0}, //down
            {-1, 0}, //up
            {0, -1}, //left
            {0, 1}, //right
        };

        for(int[] dir : directions){
            int xi = node.i + dir[0];
            int xj = node.j + dir[1];

            //checks if neighbour is in the space
            if(xi >= 0 && xi < space.length && xj >= 0 && xj < space[0].length && !space[xi][xj].isBlocked()){
                Node neighbour = space[xi][xj];
                neighbours.add(neighbour);
            }

        }
        return neighbours;
    }

    public List<Node> getShortestPath(Node node) {
        return node.getSolution();
    }

    public static void main(String[] args) {
        List<Node> blockedNodes = new ArrayList<>();
        blockedNodes.add(new Node(0, 1));
        blockedNodes.add(new Node(1, 1)); 
        blockedNodes.add(new Node(2, 1)); 
        AStar AStar = new AStar(3, 3, 0, 0, 2, 2, blockedNodes);
    }
}