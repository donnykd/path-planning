package com.gmahamed.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EntityTest {
    EntityType entity;

    @Test
    void testEntity() {
        entity = EntityType.BOX;
        assertEquals(entity.toString(), "Box", "checks if toString method returns the correct string");
  }

}
