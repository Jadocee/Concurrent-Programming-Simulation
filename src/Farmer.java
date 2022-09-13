import java.util.Enumeration;

/**
 * COMP2240 Assignment 2
 * File:   Farmer.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Farmer extends Thread {
    private final String id;

    public Farmer(String id) {
        super();
        this.id = id;
    }

    private enum Bound {
        North, South;
    }
}
