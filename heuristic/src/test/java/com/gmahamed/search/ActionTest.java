package com.gmahamed.search;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
public class ActionTest {

    @Test
    void moveEffectTest(){
        Action action = new Action(ActionType.MOVE);
        State currentState = new State(Entity.EXPLORER, new Node(0, 0));
        action.performEffect(currentState, new Node(1, 0), null);
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
    }

    @Test
    void pickEffectTest(){
        State stored = new State(Entity.EXPLORER);
        Action action = new Action(ActionType.PICK_UP);
        action.performEffect(stored, null, Item.KEY);
        assertEquals("Key", stored.getItem().toString());
    }

    @Test
    void jointMovePickTest(){
        Action move_action = new Action(ActionType.MOVE);
        Action pick_action = new Action(ActionType.PICK_UP);
        State currentState = new State(Entity.EXPLORER, new Node(0, 0));
        State stored = new State(Entity.EXPLORER);
        move_action.performEffect(currentState, new Node(1, 0), null);
        pick_action.performEffect(stored, null, Item.KEY);
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
        assertEquals("Key", stored.getItem().toString());
    }

}
