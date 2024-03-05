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
    //pull and push constructor
    public Action(ActionType action, State state1, State state2, Node node1, Node node2){
        this(action, state1, state2, node1, node2, null);
    }
    //give constructor
    public Action(ActionType action, State state1, State state2, Item item){
        this(action, state1, state2, null, null, item);
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
            case GIVE:
                giveEffect(state1, state2, item);
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

    private void pullAndPushEffect(State state1, State state2, Node node1, Node node2) {
        state1.updateNode(node1);
        state2.updateNode(node2);
    }

    private void giveEffect(State state1, State state2, Item item) {
        // Remove the item from state1's stored items
        if(state1.hasItem(item)){
            state2.store(state1.getItem(item));
        }
    }    
}
