package com.gmahamed.search;

import java.util.*;

/**
 * Represents the state of an entity in the search space.
 * It includes the current position of the entity, carried items, and previous state.
 */
public class State {
    public Entity set_entity;
    private Map<Entity, List<Object>> currentState;
    private Map<Item, Node> items = new HashMap<>();
    private State previousState;
    private boolean carried;

    /**
     * Constructor for creating a state with an entity at a specific node.
     * @param entity The entity associated with this state.
     * @param node The node representing the position of the entity.
     */
    public State(Entity entity, Node node) {
        this.set_entity = entity;
        this.currentState = new HashMap<>();
        List<Object> list = new ArrayList<>();
        list.add(node);
        currentState.put(entity, list);
    }

    /**
     * Constructor for creating a state with an item at a specific node.
     * @param item The item to be stored.
     * @param node The node representing the position of the item.
     */
    public State(Item item, Node node) {
        items.put(item, node);
        this.carried = false;
    }
    
    // NODE SPECIFIC METHODS


    /**
     * Retrieves the node entity currently is on.
     * @return The node representing the position of the entity.
     */
    public Node getNode() {
        List<Object> tempList = currentState.get(set_entity);
        return (Node) tempList.get(0);
    }

    /**
     * Updates the node entity currently is on.
     * @param node The new node representing the position of the entity.
     */
    public void updateNode(Node node) {
        List<Object> tempList = currentState.get(set_entity);
        tempList.remove(0);
        tempList.add(0, node);
    }

    /**
     * Retrieves the entity associated with this state.
     * @return The entity of this state.
     */
    public Entity getEntity() {
        return set_entity;
    }

    /**
     * Retrieves the number of elements in the value list.
     * @return The number of elements in the value list.
     */
    public int size() {
        List<Object> tempList = currentState.get(set_entity);
        return tempList.size();
    }

    // ITEM SPECIFIC METHODS

    /**
     * Retrieves the node associated with a specific item in this state.
     * @param item The item for which to retrieve the node.
     * @return The node representing the position of the item.
     */
    public Node getItemNode(Item item) {
        return items.get(item);
    }

    /**
     * Stores an item in this state.
     * @param item The item to be stored.
     */
    public void store(Item item) {
        List<Object> tempList = currentState.get(set_entity);
        tempList.add(item);
    }

    /**
     * Checks if this state contains a specific item.
     * @param item The item to check for.
     * @return True if the item is present in this state, false otherwise.
     */
    public boolean hasItem(Item item) {
        List<Object> tempList = currentState.get(set_entity);
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).toString().equals(item.toString()))
                return true;
        }
        return false;
    }

    /**
     * Gets and removes a specific item from this state.
     * @param item The item to retrieve and remove.
     * @return The retrieved item, or null if the item is not present.
     */
    public Item getItem(Item item) {
        List<Object> tempList = currentState.get(set_entity);
        for (int i = 0; i < tempList.size(); i++) {
            if (tempList.get(i).toString().equals(item.toString())) {
                Item answer = (Item) tempList.get(i);
                tempList.remove(i);
                return answer;
            }
        }
        return null;
    }

    /**
     * Sets the carried state of this state.
     * @param b True if the state is carried, false otherwise.
     */
    public void setCarried(boolean b) {
        this.carried = b;
    }
    
    /**
     * Checks if this state is carried.
     * @return True if the state is carried, false otherwise.
     */
    public boolean isCarried() {
        return carried;
    }

    /**
     * Retrieves the F value of this state set in the node class, used in A* algorithm.
     * @return The F value of this state.
     */
    public int getF() {
        return getNode().getF();
    }

    /**
     * Gets the previous state of current state.
     * @return The previous state.
     */
    public State getPreviousState() {
        return previousState;
    }

    /**
     * Sets the previous state of current state.
     * @param state The previous state.
     */
    public void setPreviousState(State state) {
        this.previousState = state;
    }

    // Equals and hashcode methods for comparing states

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
