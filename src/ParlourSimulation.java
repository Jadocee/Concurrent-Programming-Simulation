/*
 * COMP2240 Assignment 2
 * File:    ParlourSimulation.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Used to instantiate the classes required to execute the {@link AbstractParlour} and
 * manage the creation and dispatching of {@link Customer} instances
 *
 * @see P2
 * @see P3
 */
public abstract class ParlourSimulation {
    protected final AbstractParlour parlour;
    protected final AbstractTimer timer;
    private final Queue<Customer> customerQueue;

    /**
     * Constructor that takes and sets the timer and ice-cream parlour
     *
     * @param timer   the {@link AbstractTimer} used by this {@link ParlourSimulation}
     * @param parlour the {@link AbstractParlour} used by this {@link ParlourSimulation}
     */
    public ParlourSimulation(AbstractTimer timer, AbstractParlour parlour) {
        this.timer = timer;
        this.parlour = parlour;
        parlour.setTimer(this.timer);
        customerQueue = new PriorityQueue<>();
    }

    /**
     * Read in data for {@link Customer} instances from the .txt file specified by the {@link File}
     * <p>The expected format of the file is <code>"x y z \n"</code>, <br>
     * where <code>x</code> is the arrival time, <br>
     * <code>y</code> is the ID, <br>
     * <code>z</code> is the ice-cream eating time</p>
     *
     * @param file the file to be read
     * @return a {@link List} of data to be used by an instance of {@link ParlourSimulation}
     * @throws FileNotFoundException {@inheritDoc}
     * @see #loadData(List)
     */
    public static List<String> readInData(File file) throws FileNotFoundException {
        final List<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            String line;
            while (!(line = scanner.nextLine()).equals("END")) {
                data.addAll(List.of(line.split("\\s+")));
            }
        }
        return data;
    }

    /**
     * Creates instances of {@link Customer} using the data provided
     *
     * @param data the {@link List<String>} containing the data for the {@link Customer} instances to create
     * @see #readInData(File)
     */
    public void loadData(List<String> data) {
        for (int i = 0, j = 1, k = 2; k < data.size(); k = (j = (i = (k + 1)) + 1) + 1) {
            final Customer customer = new Customer(Integer.parseInt(data.get(i)), data.get(j), Integer.parseInt(data.get(k)));
            customer.setParlour(parlour);
            customerQueue.add(customer);
        }
    }

    /**
     * Starts this {@link ParlourSimulation}
     * <p>Executes the {@link AbstractTimer} and each {@link Customer} in new threads, and then waits for
     * the {@link AbstractTimer} thread to die before printing the results</p>
     *
     * @see Thread
     */
    public void start() {
        final Thread timerThread = new Thread(timer, "Timer");
        Customer next;
        while ((next = customerQueue.poll()) != null) {
            next.setTimer(timer);
            new Thread(next, next.getId()).start();
        }
        timerThread.start();
        try {
            timerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(parlour.getLog());
    }


}
