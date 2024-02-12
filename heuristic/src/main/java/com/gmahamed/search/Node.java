package com.gmahamed.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Node {
    //co-ordinates
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

    public Node(int i, int j){
        this.i = i;
        this.j = j;
        updateF();
    }

    @Override
    public String toString() {
        return ("i: " + i + ", j: " + j);
    }

    public int getG(){ return this.g;}
    public void setG(int x){
        this.g = x;
        updateF();
    }

    public int getH(){ return this.h;}
    public void setH(int x){
        this.h = x;
        updateF();
    }

    public void setParent(Node node){
        this.parent = node;
    }

    public int getF(){
        return f;
    }

    // Update f whenever g or h changes
    public void updateF(){
        f = g + h;
    }

    @Override
    public boolean equals(Object obj){
        Node node = (Node) obj;
        return this.i == node.i && this.j == node.j;
    }

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

    public void setBlocked(boolean b) {
        this.blocked = true;
    }
    public boolean isBlocked(){ return blocked;}
}
