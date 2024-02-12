package com.gmahamed.search;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AStarTest {
    AStar path;

    @BeforeEach
    void setup(){
        path = new AStar(3, 3, 0, 0, 2, 2);
    }

    @Test
    void openNodesSize(){
        assertTrue(path.openNodes.size() == 1);
    }

    @Test
    void testHCost(){
        assertEquals(path.goalNode.getH(), 0);
        assertEquals(path.startNode.getH(), 4);
    }
    @Test
    void correctPathTest(){
        List<Node> path2 = path.getShortestPath(path.currentNode);
        assertFalse(path2.isEmpty());
        assertEquals(5, path2.size());
        assertEquals("i: 0, j: 0", path2.get(0).toString());
        assertEquals("i: 2, j: 2", path2.get(4).toString());
    }

    @Test
    void correctPathWithBlockedNodesTest(){
        List<Node> blockedNodes = new ArrayList<>(){{
            add(new Node(5,0));
            add(new Node(5,1));
            add(new Node(5,2));
            add(new Node(5,4));
            add(new Node(5,3));
            add(new Node(5,6));
            add(new Node(5,7));
            add(new Node(5,8));
        }};
        AStar AStar = new AStar(9, 9, 0, 0, 8, 6, blockedNodes);
        List<Node> blockPath = AStar.getShortestPath(AStar.currentNode);

        assertFalse(blockPath.isEmpty());
        assertEquals(15, blockPath.size());
        assertEquals("i: 0, j: 0", blockPath.get(0).toString());
        assertEquals("i: 8, j: 6", blockPath.get(14).toString());
    }
}
