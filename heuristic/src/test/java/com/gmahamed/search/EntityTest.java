package com.gmahamed.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EntityTest {
    Entity entity;

    @Test
    void testEntity() {
        entity = Entity.BOX;
        assertEquals(entity.toString(), "Box", "checks if toString method returns the correct string");
  }

}
