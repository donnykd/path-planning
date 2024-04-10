package com.gmahamed.search;

/**
 * Creates either an Explorer, Box or Chest enum entity. Has a toString method that returns
 * a string of the name of each entity.
 * 
 * @author Khalid Mahamed
 *
 */


public enum Entity {
    /**
     * Entity Explorer.
     */
    EXPLORER("Explorer"), 
    /**
     * Entity Box.
     */
    BOX("Box"), 
    /**
     * Entity Chest.
     */
    CHEST("Chest");

    private String print; // used to initialise and store the string of each enum.

    private Entity(String s) {
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
