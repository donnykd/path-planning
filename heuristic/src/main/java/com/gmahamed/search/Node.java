package com.gmahamed.search;

public class Node {
    public int i, j;
    Node parent;
    int f, g, h;

    public Node(int i, int j){
        this.i = i;
        this.j = j;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return ("i: " + i + ", j: " + j);
    }
}
