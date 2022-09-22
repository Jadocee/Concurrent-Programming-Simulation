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

    public void setTimer(AbstractTimer timer) {
        this.timer = timer;
    }

    public int getArriveTime() {
        return arriveTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public String getId() {
        return id;
    }

    public int getSeatedTime() {
        return seatedTime;
    }

    public void setSeatedTime(int seatedTime) {
        this.seatedTime = seatedTime;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public void setParlour(AbstractParlour parlour) {
        this.parlour = parlour;
    }

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

    @Override
    public int compareTo(Customer o) {
        return Integer.compare(this.arriveTime, o.arriveTime);
    }
}
