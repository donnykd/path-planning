package com.gmahamed.search;

/**
 * @author Khalid Mahamed
 *
 *         Creates either an Explorer, Box or Chest enum entity. Has a toString method that returns
 *         a string of the name of each entity.
 */


public enum Entity {
    EXPLORER("Explorer"), BOX("Box"), CHEST("Chest");

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
