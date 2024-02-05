package com.gmahamed.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    PriorityQueue<Node> openNodes;
    HashSet<Node> closedNodes;

    Node[][] space;
    Node startNode;
    Node goalNode;

    public AStar(){
        space = new Node[3][3];
        openNodes = new PriorityQueue<>(Comparator.comparingInt(Node::getF));
        closedNodes = new HashSet<Node>();
        startNode = new Node(0, 0);
        goalNode = new Node(2, 2);

        for(int i=0; i < space.length; i++){
            for(int j = 0; j < space[i].length; j++){
                space[i][j] = new Node(i, j);
                space[i][j].h = Math.abs(i - goalNode.i) + (j - goalNode.j);
            }
        }

        startNode.h = Math.abs(startNode.i - goalNode.i) + Math.abs(startNode.j - goalNode.j);
        openNodes.add(startNode);

        while(openNodes.size() > 0){
            Node currentNode = openNodes.poll();
            closedNodes.add(currentNode);

            if(currentNode.equals(goalNode)){
                //find the solution
                break;
            }
        }
    }
}
