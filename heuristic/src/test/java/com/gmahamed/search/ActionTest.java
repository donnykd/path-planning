package com.gmahamed.search;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
public class ActionTest {

    @Test
    void moveEffectTest(){
        State currentState = new State(Entity.EXPLORER, new Node(0, 0));
        new Action(ActionType.MOVE, currentState, new Node(1, 0));
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
    }

    @Test
    void pickEffectTest(){
        State stored = new State(Entity.EXPLORER);
        new Action(ActionType.PICK_UP, stored, Item.KEY);
        assertEquals("Key", stored.getItem().toString());
    }

    @Test
    void jointMovePickTest(){
        State currentState = new State(Entity.EXPLORER, new Node(0, 0));
        State stored = new State(Entity.EXPLORER);
        new Action(ActionType.PICK_UP, stored, Item.KEY);
        new Action(ActionType.MOVE, currentState, new Node(1, 0));
        assertEquals("i: 1, j: 0", currentState.getNode().toString());
        assertEquals("Key", stored.getItem().toString());
    }

    @Test
    void pushAndPullTest(){
        State explorerState = new State(Entity.EXPLORER, new Node(0, 1));
        State boxState = new State(Entity.EXPLORER, new Node(0, 2));
        new Action(ActionType.PULL, explorerState, boxState, new Node(0, 0), new Node(0, 1));
        assertEquals("i: 0, j: 0", explorerState.getNode().toString());
        assertEquals("i: 0, j: 1", boxState.getNode().toString());
    }

}
