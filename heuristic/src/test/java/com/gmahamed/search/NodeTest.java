package com.gmahamed.search;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest {
    Node node;

    @BeforeEach
    void testSetup(){
        node = new Node(1, 1);
    }

    @Test
    void testToString(){
        node = new Node(10, 15);
        assertEquals(node.toString(), "i: 10, j: 15");
    }

    @Test
    void testGetF(){
        node.setG(1);
        node.setH(5);
        node.updateF();
        assertTrue(node.getF() == 6);
        node.setG(7);
        node.setH(2);
        assertTrue(node.getF() == 6);
        node.updateF();
        assertTrue(node.getF() == 9);
    }

    @Test
    void testEquals(){
        Node test1 = new Node(1, 1);
        Node test2 = new Node(2, 2);
        assertTrue(node.equals(test1));
        assertFalse(node.equals(test2));
    }

}
