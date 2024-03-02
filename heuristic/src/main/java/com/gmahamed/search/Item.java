package com.gmahamed.search;

/**
 * @author Khalid Mahamed
 *
 *         Creates either a Key, Trophy or Ladder enum entity. Has a toString method that returns
 *         a string of the name of each entity.
 */


public enum Item {
    KEY("Key"), TROPHY("Trophy"), LADDER("Ladder");

    private String print; // used to initialise and store the string of each enum.

    private Item(String s) {
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
