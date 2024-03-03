package com.gmahamed.search;

public class Action {

    //move constructor
    public Action(ActionType action, State state, Node node){
        this(action, state, null, node, null, null);
    }
    //pickup constructor
    public Action(ActionType action, State state, Item item){
        this(action, state, null, null, null, item);
    }
    public Action(ActionType action, State state1, State state2, Node node1, Node node2){
        this(action, state1, state2, node1, node2, null);
    }
    //base constructor
    public Action(ActionType action, State state1, State state2, Node node1, Node node2, Item item){
        switch (action) {
            case MOVE:
                moveEffect(state1, node1);
                break;
            case PICK_UP:
                pickEffect(state1, item);
                break;
            case PULL:
            case PUSH:
                pullAndPushEffect(state1, state2, node1, node2);
                break;
            default:
                break;
        }
    }

    private void pullAndPushEffect(State state1, State state2, Node node1, Node node2) {
        state1.updateNode(node1);
        state2.updateNode(node2);
    }
    private void pickEffect(State state, Item item) {
        state.store(item);
    }

    private void moveEffect(State state, Node node) {
        state.updateNode(node);
    }
    
}
