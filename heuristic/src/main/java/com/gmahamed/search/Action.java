package com.gmahamed.search;

public class Action {
    private ActionType action;

    public Action(ActionType action){
        this.action = action;
    }

    public void performEffect(State state, Node node, Item item) {
        switch (action) {
            case MOVE:
                moveEffect(state, node);
                break;
            case PICK_UP:
                pickEffect(state, item);
            default:
                break;
        }
    }

    private void pickEffect(State state, Item item) {
        state.store(item);
    }

    private void moveEffect(State state, Node node) {
        state.updateNode(node);
    }
    
}
