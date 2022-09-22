/*
 * COMP2240 Assignment 2
 * File:   AbstractParlour.java
 * Created: 20/09/2022
 * Author: Jaydon Cameron (C3329145)
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents the ice-cream parlour with 5 seats that {@link Customer} instances can queue for
 *
 * @see MonitorParlour
 * @see SemaphoreParlour
 */
public abstract class AbstractParlour {
    protected static final int N_SEATS = 5;
    private final List<Customer> customersLog;
    protected boolean waitFlag;
    protected int nSeated;
    protected int nWaiting;
    protected AbstractTimer timer;

    /**
     * Default constructor
     */
    protected AbstractParlour() {
        customersLog = new ArrayList<>();
        waitFlag = false;
        nWaiting = 0;
        nSeated = 0;
    }

    /**
     * Set the {@link AbstractTimer} instance for this {@link AbstractParlour}
     * <p>{@link AbstractParlour} uses an {@link AbstractTimer} instance to determine the expected seating time
     * and leaving time of {@link Customer} instances</p>
     *
     * @param timer the {@link AbstractTimer} instance
     */
    public void setTimer(AbstractTimer timer) {
        this.timer = timer;
    }

    /**
     * Enter this {@link AbstractParlour} and attempt to claim a seat
     *
     * @param customer the {@link Customer} instance entering this {@link AbstractParlour}
     */
    public abstract void enter(Customer customer) throws InterruptedException;

    /**
     * Leave this {@link AbstractParlour}
     *
     * @param customer the {@link Customer} instance leaving this {@link AbstractParlour}
     */
    public abstract void leave(Customer customer) throws InterruptedException;

    /**
     * Log the details of a visiting {@link Customer}
     * <p>Used to store the instance of the {@link Customer} following its execution</p>
     *
     * @param customer the {@link Customer} instance to log
     */
    protected void log(Customer customer) {
        customersLog.add(customer);
    }

    /**
     * Get the log of {@link Customer} instances as a {@link String}
     * <p>The log outlines the arrival time, seated time, and leave time of each {@link Customer} instance that
     * passed through this {@link AbstractParlour}</p>
     *
     * @return he log
     */
    public String getLog() {
        final StringBuilder builder = new StringBuilder(String.format("%-15s%15s%15s%15s%n", "Customer", "Arrives", "Seats", "Leaves"));
        customersLog.sort((Comparator.comparingInt(Customer::getArriveTime)));
        for (final Customer customer : customersLog) {
            builder.append(String.format("%-15s%15d%15d%15d%n", customer.getId(), customer.getArriveTime(), customer.getSeatedTime(), customer.getLeaveTime()));
        }
        return builder.toString();
    }
}
