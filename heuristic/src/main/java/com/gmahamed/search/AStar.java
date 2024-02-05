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
        space = new Node[2][2];
        openNodes = new PriorityQueue<>((Node n1, Node n2) -> {
            return Comparator.comparingInt(node -> ((Node) node).getF()).compare(n1, n2);
        });
        closedNodes = new HashSet<Node>();
        startNode = new Node(0, 0);
        goalNode = new Node(2, 2);

        openNodes.add(startNode);
    }
}
