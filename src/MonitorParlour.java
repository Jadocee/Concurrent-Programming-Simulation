/*
 * COMP2240 Assignment 2
 * File:    MonitorParlour.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

/**
 * Extends {@link AbstractParlour} to use monitors to manage mutual exclusion and synchronization
 */
public class MonitorParlour extends AbstractParlour {
    private final Object seatsMonitor;

    /**
     * Default constructor
     */
    public MonitorParlour() {
        super();
        seatsMonitor = new Object();
    }

    @Override
    public void enter(Customer customer) throws InterruptedException {
        synchronized (seatsMonitor) {
            if (waitFlag) {
                nWaiting++;
                while (waitFlag) seatsMonitor.wait();
                nWaiting--;
            }
            timer.setPause(true);
            waitFlag = ++nSeated == N_SEATS;
            customer.setSeatedTime(timer.getCurrentTime());
            customer.setLeaveTime(customer.getSeatedTime() + customer.getFinishTime());
            timer.setPause(false);
        }
    }

    @Override
    public void leave(Customer customer) {
        synchronized (seatsMonitor) {
            if (--nSeated == 0) {
                waitFlag = false;
                seatsMonitor.notifyAll();
            }
        }
        synchronized (this) {
            log(customer);
        }
        if (nSeated == 0 && nWaiting == 0) timer.setStopFlag(true);
    }
}
