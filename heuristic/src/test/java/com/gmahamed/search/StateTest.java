package com.gmahamed.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StateTest {
    State initialState;

    @BeforeEach
    void setup(){
        initialState = new State(Entity.EXPLORER, new Node(0, 0));
    }

    @Test
    //Checks that getNode method returns the location of entity
    void testGetNode() {
        initialState.getNode().toString();
        assertEquals("i: 0, j: 0", initialState.getNode().toString());
    }

    @Test
    //Checks if the mapping is updated and makes sure no new mapping is created
    void testUpdateNode() {
        Node new_node = new Node(0, 1);
        initialState.updateNode(new_node);
        assertEquals("i: 0, j: 1", initialState.getNode().toString());
        assertTrue(initialState.size() == 1);
    }
}
