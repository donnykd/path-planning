package com.gmahamed.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class State {
    private Entity set_entity;
    private Map<Entity, List<Object>> currentState;
    private Map<Item, Node> items = new HashMap<>();
    private State previousState;
    private boolean carried;

    public State(Entity entity, Node node) {
        this.set_entity = entity;
        currentState = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add(node);
        currentState.put(entity, list);
    }
    public State(Item item, Node node){
        items.put(item, node);
        this.carried = false;
    }
    // NODE SPECIFIC METHODS


    public Node getNode() {
        List<Object> tempList = currentState.get(set_entity);
        return (Node) tempList.get(0);
    }

    //when action occurs, update the current state
    public void updateNode(Node node) {
        List<Object> tempList = currentState.get(set_entity);
        tempList.remove(0);
        tempList.add(0, node);
    }

    public Entity getEntity() {
        return set_entity;
    }

    public int size() {
        List<Object> tempList = currentState.get(set_entity);
        return tempList.size();
    }

    // ITEM SPECIFIC METHODS

    public Node getItemNode(){
        return items.get(0);
    }

    public void store(Item item){
        List<Object> tempList = currentState.get(set_entity);
        tempList.add(item);
    }

    public boolean hasItem(Item item){
        List<Object> tempList = currentState.get(set_entity);
        for(int i = 0; i < tempList.size(); i++){
            if(tempList.get(i).toString().equals(item.toString()))
                return true;
        }
        return false;
    }

    public Item getItem(Item item) {
        List<Object> tempList = currentState.get(set_entity);
        for(int i = 0; i < tempList.size(); i++){
            if(tempList.get(i).toString().equals(item.toString())){
                Item answer = (Item) tempList.get(i);
                tempList.remove(i);
                return answer;
            }
        }
        return null;
    }

    public void setCarried(boolean b){
        this.carried = b;
    }
    
    public boolean isCarried() {
        return carried;
    }

    public int getF() {
        return getNode().getF();
    }

    public State getPreviousState() {
        return previousState;
    }

    public void setPreviousState(State state) {
        this.previousState = state;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null){
            return false;
        }
        if(obj instanceof State){
            State state = (State) obj;  
            return this.getNode().equals(state.getNode());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentState);
    }


}
