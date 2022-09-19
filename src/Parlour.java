import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class Parlour {
    private final Semaphore seatsLock;
    private final Semaphore serveLock;
    private final Semaphore entryLock;
    private final SortedMap<String, Map<String, Integer>> log;
    private volatile int tCurrent;

    public Parlour() {
        tCurrent = 0;
        seatsLock = new Semaphore(5);
        entryLock = new Semaphore(1);
        serveLock = new Semaphore(5);
        log = new TreeMap<>();
    }

    public int gettCurrent() {
        return tCurrent.get();
    }

    public Semaphore getEntryLock() {
        return entryLock;
    }

    public void arrive(Customer customer) {
//        if (tCurrent < customer.gettArrival()) tCurrent += customer.gettArrival();
        tCurrent = customer.gettArrival();
        log.put(customer.getId(), new HashMap<>(Map.of("arrives", customer.gettArrival())));
    }

    public void seat(Customer customer) {
        log.get(customer.getId()).put("seats", tCurrent);
//        System.out.printf("%-8s\t%7s\t%5s\t%6s\n", "Customer", "Arrives", "Seats", "Leaves");
//        System.out.printf("%-2.8s\t%d\t%d\t%d\n", customer.gettArrival(), customer.get);
    }

    public void leave(Customer customer) {
        tCurrent = customer.gettLeave();
        log.get(customer.getId()).put("leaves", tCurrent);
    }

    public Semaphore getSeatsLock() {
        return seatsLock;
    }
    public String getLog() {
        final StringBuilder builder = new StringBuilder(String.format("%-15s%15s%15s%15s%n", "Customer", "Arrives", "Seats", "Leaves"));
        for (final Map.Entry<String, Map<String, Integer>> entry : log.entrySet()) {
            builder.append(String.format("%-15s%15d%15d%15d%n", entry.getKey(), entry.getValue().get("arrives"), entry.getValue().get("seats"), entry.getValue().get("leaves")));
        }
        return builder.toString();
    }
}
