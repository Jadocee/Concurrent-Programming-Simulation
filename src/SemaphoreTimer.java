/*
 * COMP2240 Assignment 2
 * File:    SemaphoreTimer.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

import java.util.concurrent.Semaphore;

/**
 * Extends {@link AbstractTimer} to use {@link Semaphore} to manage mutual exclusion and synchronization
 *
 * @see MonitorTimer
 */
public class SemaphoreTimer extends AbstractTimer {
    private final Semaphore lock;

    /**
     * Default constructor
     * Creates a {@link Semaphore} instance to manage mutual exclusion
     */
    public SemaphoreTimer() {
        super();
        lock = new Semaphore(1);
    }

    @Override
    public void setPause(boolean pauseState) throws InterruptedException {
        if (pauseState) lock.acquire();
        else lock.release();
    }

    @Override
    public void run() {
        while (!stopFlag) {
            try {
                Thread.sleep(500);
                lock.acquire();
                currentTime++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.release();
            }
        }
    }
}
