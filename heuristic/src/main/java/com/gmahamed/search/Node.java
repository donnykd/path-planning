package com.gmahamed.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * Creates a node used for A* Search algorithm.
 * Coordinates, parent node and cost information will be used in assistance for the A* search.
 * 
 * @author Khalid Mahamed
 *         
 * 
*/
public class Node {

    /**
     * The co-ordinates of the node
     */
    public int i, j;

    //parent node
    private Node parent;

    // Boolean to determine if node is traversable
    private boolean blocked;

    //f(n) = g(n) + h(n)
    //f(n) = estimated total cost of path through n to goal
    //g(n) = actual cost to reach n
    //h(n) = estimated cost to goal from n
    private int g, h, f;

    /**
     * Creates a node used for the A* Search algorithm.
     * 
     * @param i The x-coordinate of the node.
     * @param j The y-coordinate of the node.
     */
    public Node(int i, int j) {
        //instantiating initial co-ordinates aswell as the cost of f
        this.i = i;
        this.j = j;
        updateF();
    }

    
    /** Returns a string of both the i and j co-ordinates.
     * 
     * @return String of the node co-ordinates.
     */
    @Override
    //
    public String toString() {
        return ("i: " + i + ", j: " + j);
    }

    
    /** Returns the cost to reach the current node from the start node.
     * 
     * @return Int G the cost to reach the current node.
     */
    public int getG() {
        return this.g;
    }
    
    
    /** 
     * Sets a new cost to reach current node from start node while also updating the f cost.
     * 
     * @param x New number to be set as the g cost.
     */
    public void setG(int x) {
        this.g = x;
        updateF();
    }
    
    
    /** 
     * returns the heuristic cost from current node to the goal node.
     * 
     * @return Int H the cost from current node to the goal.
     */
    public int getH() {
        return this.h;
    }
    
    
    /** 
     * Sets a new heuristic cost from current node to the goal node while also updating the f cost.
     * 
     * @param x New number to be set as the h cost.
     */
    public void setH(int x){
        this.h = x;
        updateF();
    }

    
    /**Sets a parent node to the current node calling the function.
     * 
     * @param node To bet set as parent node.
     */
    public void setParent(Node node){
        this.parent = node;
    }

    
    /** 
     * Returns an estimate total cost to reach goal node through the current node.
     * 
     * @return Int F estimated total cost.
     */
    public int getF(){
        return f;
    }

    // Updates f whenever g or h changes.
    /**
     * Updates the total estimated cost to reach the goal node through the current node. 
     */
    public void updateF(){
        f = g + h;
    }

    
    /** 
     * Compares the coordinates of two nodes to check if they are the same node.
     * 
     * @param obj The object to be compared to the node.
     * @return Boolean determining if nodes are equal.
     */
    @Override
    public boolean equals(Object obj){
        Node node = (Node) obj;
        if(node == null){
            return false;
        }
        return this.i == node.i && this.j == node.j;
    }

    
    /** 
     * Returns a list of nodes reversed, starting from start node to goal node while assigning a parent node to each node.
     * 
     * @return A List of Node objects representing the solution path from the start node to the goal node.
     */
    public List<Node> getSolution() {
        List<Node> path = new ArrayList<>();
        Node current = this;

        while(current != null){
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        return path;
    }

    
    /** 
     * @param b Boolean value that value blocked will be assigned as.
     */
    public void setBlocked(boolean b) {
        this.blocked = b;
    }
    
    
    /** 
     * Checks if node is blocked.
     * 
     * @return Boolean value to determine if node is blocked.
     */
    public boolean isBlocked() {
        return blocked;
    }
    
}
