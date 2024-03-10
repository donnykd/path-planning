package com.gmahamed.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class ActionTest {
    State currentState;

    @BeforeEach
    void setup(){
        currentState = new State(Entity.EXPLORER, new Node(0, 0));
    }

    @Test
    void moveEffectTest(){
        new Action(ActionType.MOVE, currentState, new Node(1, 0));
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
    }

    @Test
    void pickEffectTest(){
        new Action(ActionType.PICK_UP, currentState, Item.KEY);
        assertEquals("Key", currentState.getItem(Item.KEY).toString());
    }

    @Test
    void jointMovePickTest(){
        new Action(ActionType.PICK_UP, currentState, Item.KEY);
        new Action(ActionType.MOVE, currentState, new Node(1, 0));
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
        assertEquals("Key", currentState.getItem(Item.KEY).toString());
    }

    @Test
    void pushAndPullTest(){
        State boxState = new State(Entity.EXPLORER, new Node(0, 1));
        new Action(ActionType.PUSH, currentState, boxState, new Node(0, 1), new Node(0, 2));
        assertEquals("i: 0, j: 1", currentState.getNode().toString());
        assertEquals("i: 0, j: 2", boxState.getNode().toString());
    }

    @Test
    void giveEffectTest(){
        State state2 = new State(Entity.EXPLORER, new Node(0, 1));
        currentState.store(Item.TROPHY);
        new Action(ActionType.GIVE, currentState, state2, Item.TROPHY);
        assertFalse(currentState.hasItem(Item.TROPHY));
        assertTrue(state2.hasItem(Item.TROPHY));
    }
    @Test
    void openEffectTest(){
        State chest = new State(Entity.CHEST, new Node(1,0));
        currentState.store(Item.KEY);
        new Action(ActionType.OPEN, currentState, chest, Item.KEY);
        assertFalse(currentState.hasItem(Item.KEY));
        assertTrue(chest.hasItem(Item.KEY));
    }

}
