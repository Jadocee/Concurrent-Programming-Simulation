import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * COMP2240 Assignment 2
 * File:   Bridge.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Bridge {
    private int neon;
    private final int steps;
    private final Semaphore lock;
//    private final Island southIsland;
//    private final Island northIsland;
    private final Queue<Farmer> crossing;

    public Bridge() {
        neon = 0;
        steps = 20;
//        southIsland = new Island();
//        northIsland = new Island();
        crossing = new LinkedList<>();
        lock = new Semaphore(2);
    }

    public Semaphore getLock() {
        return lock;
    }

    public void cross(Farmer farmer) throws InterruptedException {
        int steps = 0;
        while (steps < this.steps) {
            steps += 5;
            System.out.printf("%s:\tCrossing bridge Step %d.\n", farmer.getId(), steps);
            Thread.sleep(20);
        }
        System.out.println("Across the bridge.");
        System.out.println("NEON = " + ++neon);
    }
}
