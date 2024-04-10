package com.gmahamed.search;

/**
 * Creates an ActionType Enum, used for defining what actions to take. 
 * Has a toString method that returns a string of the name of each entity.
 * 
 * @author Khalid Mahamed
 *
 */
public enum ActionType {
    /**
     * Move action.
     */
    MOVE("Move"), 
    /**
     * Pick up action.
     */
    PICK_UP("Pick_up"), 
    /**
     * Pull action.
     */
    PULL("Pull"), 
    /**
     * Push action.
     */
    PUSH("Push"), 
    /**
     * Give action.
     */
    GIVE("Give"), 
    /**
     * Open action.
     */
    OPEN("Open"),
    /**
     * Unlock action.
     */
    UNLOCK("Unlock"), 
    /**
     * Remove key action.
     */
    REMOVE_KEY("Remove_key"), 
    /**
     * Lock action.
     */
    LOCK("Lock"), 
    /**
     * Climb action.
     */
    CLIMB("Climb"), 
    /**
     * Claim action.
     */
    CLAIM("Claim"), 
    /**
     * Place down action.
     */
    PLACE_DOWN("Place_down");

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
