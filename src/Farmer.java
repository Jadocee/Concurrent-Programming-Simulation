import java.util.concurrent.Semaphore;

/**
 * COMP2240 Assignment 2
 * File:   Farmer.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Farmer extends Thread {
    private final String id;
    private final Semaphore sem;
    private final Bridge bridge;

    public Farmer(Bridge bridge, String id) {
        super(id);
        this.id = id;
        this.bridge = bridge;
        sem = bridge.getLock();
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s: Waiting for bridge: Going towards %s", id, "?");
            bridge.getLock().acquire();

            try {
                bridge.cross(this);
            } finally {
                bridge.getLock().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
