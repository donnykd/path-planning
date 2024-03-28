package com.gmahamed.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ItemsTest {
    Item item;

    @Test
    void testItem() {
        item = Item.KEY;
        assertEquals(item.toString(), "Key", "checks if toString method returns the correct string");
  }
    
}
