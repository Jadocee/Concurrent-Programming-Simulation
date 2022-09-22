/*
 * COMP2240 Assignment 2
 * File:    MonitorTimer.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

/**
 * Extends {@link AbstractTimer} to use monitors to manage mutual exclusion and synchronization
 */
public class MonitorTimer extends AbstractTimer {
    private boolean pauseFlag;

    /**
     * Default constructor
     */
    public MonitorTimer() {
        super();
        pauseFlag = false;
    }

    /**
     * {@inheritDoc}
     * <p>Enforces <code>synchronized</code> access to this method</p>
     *
     * @param pauseState {@code true} to pause this {@link AbstractTimer}
     * @throws InterruptedException
     */
    @Override
    public synchronized void setPause(boolean pauseState) throws InterruptedException {
        pauseFlag = pauseState;
    }

    /**
     * Runs this {@link MonitorTimer}
     * <p>Sleeps for 500 ms before incrementing the current time, pausing or stopping based on the relative flags</p>
     *
     * @see #pauseFlag
     * @see #stopFlag
     */
    @Override
    public void run() {
        while (!stopFlag) {
            try {
                do Thread.sleep(500);
                while (pauseFlag);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                currentTime++;
            }
        }
    }
}
