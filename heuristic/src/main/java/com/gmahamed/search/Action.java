package com.gmahamed.search;

public class Action {
    private ActionType action;

    public Action(ActionType action){
        this.action = action;
    }

    public void performEffect(State state, Node node) {
        switch (action) {
            case MOVE:
                moveEffect(state, node);
                break;
        
            default:
                break;
        }
    }

    private void moveEffect(State state, Node node) {
        state.updateNode(node);
    }
    
}
