package com.gmahamed.search;

import java.util.HashMap;

public class State {
    private Entity set_entity;
    private HashMap<Entity, Node> currentState = new HashMap<Entity, Node>();

    public State(Entity entity, Node node) {
        this.set_entity = entity;
        currentState.put(entity, node);
    }

    //when action occurs, update the current state
    public void updateNode(Node node) {
        currentState.replace(set_entity, node);
    }

    public Node getNode() {
        return currentState.get(set_entity);
    }

    public int size() {
        return currentState.size();
    }
}
