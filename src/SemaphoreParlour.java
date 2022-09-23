/*
 * COMP2240 Assignment 2
 * File:    SemaphoreParlour.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

import java.util.concurrent.Semaphore;

/**
 * Extends {@link AbstractParlour} to use {@link Semaphore} to manage mutual exclusion and synchronization
 *
 * @see MonitorParlour
 */
public class SemaphoreParlour extends AbstractParlour {
    private final Semaphore logLock;
    private final Semaphore block;
    private final Semaphore valueLock;

    /**
     * Default constructor
     * <p>Creates {@link Semaphore} instances for managing mutual exclusion and synchronization</p>
     */
    public SemaphoreParlour() {
        super();
        block = new Semaphore(0, true);
        valueLock = new Semaphore(1, true);
        logLock = new Semaphore(1);
    }

    @Override
    public void enter(Customer customer) throws InterruptedException {
        try {
            // Wait to mutate member variables; acquire mutex lock
            valueLock.acquire();
            if (waitFlag) {
                nWaiting++;
                valueLock.release();
                // Wait for all seats to clear; has mutex on wake
                block.acquire();
                nWaiting--;
            }
            try {
                timer.setPause(true);
                customer.setSeatedTime(timer.getCurrentTime());
                customer.setLeaveTime(customer.getSeatedTime() + customer.getFinishTime());
                waitFlag = ++nSeated == N_SEATS;
            } finally {
                timer.setPause(false);
            }
        } finally {
            if (!waitFlag && nWaiting > 0) block.release();
            else valueLock.release();
        }
    }

    @Override
    public void leave(Customer customer) throws InterruptedException {
        try {
            valueLock.acquire();
            if (--nSeated == 0) waitFlag = false;
        } finally {
            if (!waitFlag && nWaiting > 0) block.release();
            else valueLock.release();
        }
        try {
            logLock.acquire();
            log(customer);
        } finally {
            logLock.release();
        }
        if (nSeated == 0 && nWaiting == 0) timer.setStopFlag(true);
    }
}
