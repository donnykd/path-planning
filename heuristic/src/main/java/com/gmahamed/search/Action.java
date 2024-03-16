package com.gmahamed.search;

import java.util.List;

public class Action {

    //move constructor
    public Action(ActionType action, State state, Node node){
        this(action, state, null, node, null, null, null);
    }
    public Action(ActionType action, State state, List<Node> list){
        this(action, state, null, null, null, null, list);
    }
    //pickup constructor
    public Action(ActionType action, State state, Item item){
        this(action, state, null, null, null, item, null);
    }
    //pull and push constructor
    public Action(ActionType action, State state1, State state2, Node node1, Node node2){
        this(action, state1, state2, node1, node2, null, null);
    }
    //give constructor
    public Action(ActionType action, State state1, State state2, Item item){
        this(action, state1, state2, null, null, item, null);
    }
    //base constructor
    public Action(ActionType action, State state1, State state2, Node node1, Node node2, Item item, List<Node> list){
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
            case OPEN:
                giveAndOpenEffect(state1, state2, item);
            case UNLOCK:
                unlockEffect(state1, list);    
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

    private void giveAndOpenEffect(State state1, State state2, Item item) {
        // Remove the item from state1's stored items
        if(state1.hasItem(item)){
            state2.store(state1.getItem(item));
        }
    }

    private void unlockEffect(State state1, List<Node> list) {
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).isNeighbour(state1.getNode()) && list.get(i).isDoor()){
                list.get(i).setBlocked(false);
                state1.getItem(Item.KEY);
            }
        }
    }
}
