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
    private static int neon;
    private final int steps;
    private final Semaphore lock;

    private volatile static boolean available;

    public Bridge() {
        neon = 0;
        steps = 20;
        lock = new Semaphore(1);
        available = true;
    }

    public Semaphore getLock() {
        return lock;
    }

    public boolean isAvailable() {
        return available;
    }

    public void cross(Farmer farmer) throws InterruptedException {
        int steps = 0;
        while (steps < this.steps) {
            steps += 5;
            System.out.printf("%s:\tCrossing bridge Step %d.\n", farmer.getId(), steps);
            Thread.sleep(20);
        }
        if (farmer.getBound().equals(Farmer.Bound.N)) {
            farmer.setBound(Farmer.Bound.S);
        } else {
            farmer.setBound(Farmer.Bound.N);
        }
        System.out.printf("%s:\tAcross the bridge.\n", farmer.getId());
        System.out.println("NEON = " + ++neon);

        if (neon >= 100) {
            available = false;
            System.out.println("Terminating.");
        }
    }
}
