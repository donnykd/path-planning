package com.gmahamed.search;

import java.util.*;

public class State {
    private Entity set_entity;
    private Map<Entity, List<Object>> currentState;
    private Map<Item, Node> items = new HashMap<>();
    private State previousState;
    private boolean carried;

    public State(Entity entity, Node node) {
        this.set_entity = entity;
        this.currentState = new HashMap<>();
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

    public Node getItemNode(Item item){
        return items.get(item);
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        State other = (State) obj;
        if (currentState == null) {
            if (other.currentState != null)
                return false;
        } else if (!currentState.equals(other.currentState))
            return false;
        if (items == null) {
            if (other.items != null)
                return false;
        } else if (!items.equals(other.items))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((currentState == null) ? 0 : currentState.hashCode());
        result = prime * result + ((items == null) ? 0 : items.hashCode());
        return result;
    }



}
