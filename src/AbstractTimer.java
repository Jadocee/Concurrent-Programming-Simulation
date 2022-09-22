/*
 * COMP2240 Assignment 2
 * File:   Abstract.java
 * Created: 20/09/2022
 * Author: Jaydon Cameron (C3329145)
 */

/**
 * Abstract class used to emulate time
 *
 * @see SemaphoreTimer
 * @see MonitorTimer
 */
public abstract class AbstractTimer implements Runnable {
    protected int currentTime;
    protected boolean stopFlag;

    /**
     * Default constructor
     */
    protected AbstractTimer() {
        currentTime = -1;
        stopFlag = false;
    }

    /**
     * Get the current time of this {@link AbstractTimer}
     *
     * @return the current time
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Set if this {@link AbstractTimer} should stop
     *
     * @param stopFlag whether this {@link AbstractTimer} should stop
     */
    public void setStopFlag(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * Pause this {@link AbstractTimer}
     *
     * @param pauseState {@code true} to pause this {@link AbstractTimer}
     */
    public abstract void setPause(boolean pauseState) throws InterruptedException;

}
