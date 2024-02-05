package com.gmahamed.search;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class NodeTest {
    Node node;

    @Test
    void testSetup(){
        node = new Node(1, 1);
    }

    @Test
    void testToString(){
        node = new Node(10, 15);
        assertEquals(node.toString(), "i: 10, j: 15");
    }

}
