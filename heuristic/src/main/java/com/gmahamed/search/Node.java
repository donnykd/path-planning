package com.gmahamed.search;

import java.util.ArrayList;
import java.util.List;

public class Node {
    //co-ordinates
    public int i, j;
    //parent node
    Node parent;
    //f(n) = g(n) + h(n)
    //f(n) = estimated total cost of path through n to goal
    //g(n) = actual cost to reach n
    //h(n) = estimated cost to goal from n
    int g, h;
    int f;
    boolean solution;

    public Node(int i, int j){
        this.i = i;
        this.j = j;
        updateF();
    }

    @Override
    public String toString() {
        return ("i: " + i + ", j: " + j);
    }

    public void setG(int x){
        this.g = x;
    }

    public void setH(int x){
        this.h = x;
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

        return path;
    }
}
