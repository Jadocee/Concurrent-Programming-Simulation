import java.util.concurrent.Semaphore;

/**
 * COMP2240 Assignment 2
 * File:   Bridge.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Bridge {
    private final int steps;
    private final Semaphore lock;
    private int neon;
    private volatile boolean exit;


    public Bridge() {
        neon = 0;
        exit = false;
        steps = 20;
        lock = new Semaphore(1);
    }

    public Semaphore getLock() {
        return lock;
    }

    public boolean exit() {
        return exit;
    }

    public void cross(Farmer farmer) throws InterruptedException {
        int steps = 0;
        while (steps < this.steps) {
            if ((steps += 5) == this.steps) {
                System.out.printf("%s:\tAcross the bridge.\n", farmer.getId());
                System.out.println("NEON = " + ++neon);
            } else {
                System.out.printf("%s:\tCrossing bridge Step %d.\n", farmer.getId(), steps);
                Thread.sleep(20);
            }
        }
        if (neon >= 100) stop();
    }

    private void stop() {
        exit = true;
        System.out.println("Terminating.");
    }
}
