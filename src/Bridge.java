/**
 * COMP2240 Assignment 2
 * File:   Bridge.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Bridge {
    private int neon;
    private final Island southIsland;
    private final Island northIsland;

    public Bridge() {
        neon = 0;
        southIsland = new Island();
        northIsland = new Island();

    }
}
