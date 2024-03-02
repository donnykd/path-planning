package com.gmahamed.search;

/**
 * @author Khalid Mahamed
 *
 *         Creates an ActionType Enum, used for defining what actions to take. Has a toString method that returns
 *         a string of the name of each entity.
 */
public enum ActionType {
    MOVE("Move"), PICK_UP("Pick_up"), PULL("Pull"), PUSH("Push"), GIVE("Give"), OPEN("Open"),
    UNLOCK("Unlock"), REMOVE_KEY("Remove_key"), LOCK("Lock"), CLIMB("Climb"), CLAIM("Claim"), PLACE_DOWN("Place_down");

    private String print; // used to initialise and store the string of each enum.

    private ActionType(String s) {
        print = s;
    }

    /**
    * Returns the string of each enum.
    */
    @Override
    public String toString() {
      return print;
    }  
}
