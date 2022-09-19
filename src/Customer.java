/**
 *
 */
public class Customer implements Runnable, Comparable<Customer> {
    private final int tArrival;
    private final int tLeave;
    private Parlour parlour;
    private final String id;


    public Customer(int tArrival, String id, int tFinish) {
        this.id = id;
        this.tLeave = tFinish;
        this.tArrival = tArrival;
    }

    public void setParlour(Parlour parlour) {
        this.parlour = parlour;
    }

    public String getId() {
        return id;
    }

    public int gettArrival() {
        return tArrival;
    }

    public int gettLeave() {
        return tLeave;
    }

    @Override
    public void run() {
        try {

            try {
                parlour.getEntryLock().acquire();
                parlour.arrive(this);
            } finally {
                parlour.getEntryLock().release();
            }

            try {
                parlour.getSeatsLock().acquire();
                parlour.seat(this);
                parlour.leave(this);
            } finally {
                parlour.getSeatsLock().release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Customer o) {
        return Integer.compare(this.tArrival, o.tArrival);
    }
}
