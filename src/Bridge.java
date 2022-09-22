import java.util.concurrent.Semaphore;

/*
 * COMP2240 Assignment 2
 * File:   Bridge.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 */


/**
 * Represents a single-lane bridge used by {@link Farmer} to cross between a north island and south island.
 */
public class Bridge {
    private static final int STEPS = 20;
    private final Semaphore lock;
    private int neon;
    private volatile boolean exit;


    /**
     * Default constructor
     * <p>Initializes member variables and creates a new {@link Semaphore} to manage access to the bridge</p>
     */
    public Bridge() {
        neon = 0;
        exit = false;
        lock = new Semaphore(1, true);
    }

    /**
     * Get the {@link Semaphore} instance used to access and cross this {@link Bridge}
     *
     * @return the {@link Semaphore} to cross this {@link Bridge}
     */
    public Semaphore getLock() {
        return lock;
    }

    /**
     * Get the current state of the flag used to indicate whether the system should finish up
     *
     * @return {@code true} if the program is ready to exit
     * <p>{@code false} if otherwise</p>
     */
    public boolean exit() {
        return exit;
    }

    public void cross(Farmer farmer) throws InterruptedException {
        int steps = 0;
        while (steps < STEPS) {
            if ((steps += 5) == STEPS) {
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
