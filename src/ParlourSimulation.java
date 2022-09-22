import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 */
public abstract class ParlourSimulation {
    protected final AbstractParlour parlour;
    protected final AbstractTimer timer;
    private final Queue<Customer> customerQueue;

    public ParlourSimulation(AbstractTimer timer, AbstractParlour parlour) {
        this.timer = timer;
        this.parlour = parlour;
        parlour.setTimer(this.timer);
        customerQueue = new PriorityQueue<>();
    }

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

    public void loadData(List<String> data) {
        for (int i = 0, j = 1, k = 2; k < data.size(); k = (j = (i = (k + 1)) + 1) + 1) {
            final Customer customer = new Customer(Integer.parseInt(data.get(i)), data.get(j), Integer.parseInt(data.get(k)));
            customer.setParlour(parlour);
            customerQueue.add(customer);
        }
    }

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
