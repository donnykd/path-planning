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
        path = new AStar(3, 3, new State(Entity.EXPLORER, new Node(0, 0)), new State(Entity.EXPLORER, new Node(2, 1)));
    }

    @Test //1st Test
    //Test to check if condition of finding the solution is met
    void openNodesSize(){
        assertTrue(path.openStates.size() > 0);
    }

    @Test //2nd Test
    //Test to check if assignment of Hcost was done correctly
    void testHCost(){
        assertEquals(path.goalState.getNode().getH(), 0);
        assertEquals(path.startState.getNode().getH(), 3);
    }

    @Test //3rd Test
    //Test to check if path is correctly generated 
    void correctPathTest(){
        List<String> expectedPath = new ArrayList<>();
        expectedPath.add("Move i: 0, j: 0 -> i: 1, j: 0");
        expectedPath.add("Move i: 1, j: 0 -> i: 1, j: 1");
        expectedPath.add("Move i: 1, j: 1 -> i: 2, j: 1");

        List<String> generatedPath = path.reconstructActions(path.currentState);

        assertEquals(expectedPath, generatedPath);
    }

    @Test //4th Test
    //Test to check if path is correctly generated with blocked nodes
    void correctPathWithBlockedNodesTest(){
        List<Node> blockedNodes = new ArrayList<>(){{
            add(new Node(0,1));
            add(new Node(1,1));
            add(new Node(2,1));
            add(new Node(3,1));
        }};
        path = new AStar(5, 5, new State(Entity.EXPLORER, new Node(0, 0)), new State(Entity.EXPLORER, new Node(0, 3)), blockedNodes);
        List<String> generatedPath = path.reconstructActions(path.currentState);

        assertFalse(generatedPath.isEmpty());
        assertEquals(11, generatedPath.size());
        assertEquals("Move i: 0, j: 0 -> i: 1, j: 0", generatedPath.get(0).toString());
        assertEquals("Move i: 1, j: 3 -> i: 0, j: 3", generatedPath.get(10).toString());
    }
}
