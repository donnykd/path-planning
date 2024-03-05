package com.gmahamed.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class State {
    private Entity set_entity;
    private Map<Entity, List<Object>> currentState;

    public State(Entity entity, Node node) {
        this.set_entity = entity;
        currentState = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add(node);
        currentState.put(entity, list);
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

    public int size() {
        List<Object> tempList = currentState.get(set_entity);
        return tempList.size();
    }

    // ITEM SPECIFIC METHODS



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

}
