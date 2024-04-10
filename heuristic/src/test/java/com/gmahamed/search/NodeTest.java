package com.gmahamed.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest {
    Node node;

    @BeforeEach
    void testSetup(){
        node = new Node(1, 1);
    }

    @Test //1st Test
    //This test checks if toString method for the node is correct.
    void testToString(){
        node = new Node(10, 15);
        assertEquals(node.toString(), "i: 10, j: 15");
    }

    @Test //2nd Test
    //This test checks if f cost is changed by setting different g and h costs on the same node, inadvertantly also testing
    //setG and setH
    void testGetF(){
        node.setG(1);
        node.setH(5);
        assertTrue(node.getF() == 6);
        node.setG(7);
        node.setH(2);
        assertTrue(node.getF() == 9);
    }

    @Test //3rd Test
    //This test checks if the method Equals works as intended
    void testEquals(){
        Node test1 = new Node(1, 1);
        Node test2 = new Node(2, 2);
        assertTrue(node.equals(test1));
        assertFalse(node.equals(test2));
    }

    @Test //4th Test
    //This test checks if the solution is correct and set in the right order
    void testSolution(){
        node = new Node(3, 3);
        Node parent = new Node(3, 2);
        Node grandparent = new Node(2, 2);
        Node greatgp = new Node(1, 2);

        node.setParent(parent);
        parent.setParent(grandparent);
        grandparent.setParent(greatgp);

        List<Node> solution = node.getSolution();

        assertEquals(4, solution.size());
        assertEquals(greatgp, solution.get(0));
    }

}
