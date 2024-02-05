package com.gmahamed.search;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AStarTest {
    AStar path;

    @BeforeEach
    void setup(){
        path = new AStar();
    }

    @Test
    void openNodesSize(){
        assertTrue(path.openNodes.size() == 1);
    }

    @Test
    void closeNodesSize(){
        assertTrue(path.closedNodes.size() == 0);
    }

    @Test
    void openNodesPop(){
        Node node = new Node(0, 0);
        assertTrue(path.openNodes.poll().toString().equals(node.toString()));
    }

}
