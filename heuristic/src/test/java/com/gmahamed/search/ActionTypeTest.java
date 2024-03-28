package com.gmahamed.search;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ActionTypeTest {
    ActionType action;

    @Test
    void testEntity() {
        action = ActionType.MOVE;
        assertEquals(action.toString(), "Move", "checks if toString method returns the correct string");
  }

}
