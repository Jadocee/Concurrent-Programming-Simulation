/*
 * COMP2240 Assignment 2
 * File:   Customer.java
 * Created: 20/09/2022
 * Author: Jaydon Cameron (C3329145)
 */

/**
 * Represents a customer of the ice-cream parlour
 *
 * @see AbstractParlour
 */
public class Customer implements Runnable, Comparable<Customer> {
    private final int arriveTime;
    private final int finishTime;
    private final String id;
    private AbstractParlour parlour;
    private int seatedTime;
    private int leaveTime;
    private AbstractTimer timer;


    /**
     * Default constructor
     *
     * @param arriveTime the time that this {@link Customer} will enter the ice-cream parlour
     * @param id         the ID of this {@link Customer} (e.g., <code>C1</code>, <code>C2</code>, etc.)
     * @param finishTime the time that this {@link Customer} will take to finish their ice-cream
     */
    public Customer(int arriveTime, String id, int finishTime) {
        this.id = id;
        this.finishTime = finishTime;
        this.arriveTime = arriveTime;
    }

    /**
     * Sets the {@link AbstractTimer} of this {@link Customer}
     * <p>{@link AbstractTimer} is used to manage the arrival time and leave time of each {@link Customer}</p>
     *
     * @param timer the {@link AbstractTimer}
     */
    public void setTimer(AbstractTimer timer) {
        this.timer = timer;
    }

    /**
     * Gets the time that this {@link Customer} will have entered the {@link AbstractParlour}
     *
     * @return the arrival time
     */
    public int getArriveTime() {
        return arriveTime;
    }

    /**
     * Gets the time it takes for this {@link Customer} to finish their ice-cream (i.e., how long this {@link Customer}
     * stays in the {@link AbstractParlour} after getting a seat)
     *
     * @return the time to finish
     */
    public int getFinishTime() {
        return finishTime;
    }

    /**
     * Gets the ID of this {@link Customer}
     *
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the time that this {@link Customer} was seated in the {@link AbstractParlour}
     *
     * @return the seating time
     */
    public int getSeatedTime() {
        return seatedTime;
    }

    /**
     * Sets the time that this {@link Customer} was seated in the {@link AbstractParlour}
     *
     * @param seatedTime the seating time
     */
    public void setSeatedTime(int seatedTime) {
        this.seatedTime = seatedTime;
    }

    /**
     * Gets the time that this {@link Customer} left the {@link AbstractParlour}
     *
     * @return the leaving time
     */
    public int getLeaveTime() {
        return leaveTime;
    }

    /**
     * Sets the time that this {@link Customer} left the {@link AbstractParlour}
     *
     * @param leaveTime the leaving time
     */
    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    /**
     * Sets the {@link AbstractParlour} that this {@link Customer} will visit
     *
     * @param parlour the ice-cream parlour
     */
    public void setParlour(AbstractParlour parlour) {
        this.parlour = parlour;
    }

    /**
     * Runs this {@link Customer}
     * <p>Waits for the current time in {@link AbstractTimer} to equal the set arrival time and leave time before
     * entering or leaving the {@link AbstractParlour}, respectively</p>
     *
     * @see #arriveTime
     * @see #leaveTime
     */
    @Override
    public void run() {
        try {
            while (timer.getCurrentTime() < arriveTime) Thread.sleep(10);
            parlour.enter(this);

            while (timer.getCurrentTime() < leaveTime) Thread.sleep(10);
            parlour.leave(this);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compares the arrival time of this {@link Customer} with another {@link Customer}
     *
     * @param o the {@link Customer} to be compared.
     * @return {@inheritDoc}
     */
    @Override
    public int compareTo(Customer o) {
        return Integer.compare(this.arriveTime, o.arriveTime);
    }
}
