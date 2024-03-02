package com.gmahamed.search;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ActionTest {
    Action action;

    @BeforeEach
    void setup(){
        action = new Action(ActionType.MOVE);
    }

    @Test
    void moveEffectTest(){
        State currentState = new State(Entity.EXPLORER, new Node(0, 0));
        action.performEffect(currentState, new Node(1, 0));
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
    }
}
