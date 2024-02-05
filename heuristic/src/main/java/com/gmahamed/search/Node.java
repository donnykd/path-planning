package com.gmahamed.search;

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
    int f = g + h;

    public Node(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        return ("i: " + i + ", j: " + j);
    }
    public int getF(){
        return f;
    }
}
