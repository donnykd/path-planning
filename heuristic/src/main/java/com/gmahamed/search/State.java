package com.gmahamed.search;

import java.util.HashMap;

public class State {
    private HashMap<EntityType, Node> currentState = new HashMap<EntityType, Node>();

    public State(EntityType entity, Node node) {
        currentState.put(entity, node);
    }
}
