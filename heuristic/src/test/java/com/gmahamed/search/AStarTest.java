package com.gmahamed.search;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
