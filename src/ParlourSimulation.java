import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 */
public class ParlourSimulation {
    private final Parlour parlour;
    private final Queue<Customer> customerQueue;

    public ParlourSimulation() {
        parlour = new Parlour();
        customerQueue = new PriorityQueue<>();
    }

    public void loadData(List<String> data) {
        for (int i = 0, j = 1, k = 2; k < data.size(); k = (j = (i = (k + 1)) + 1) + 1) {
            final Customer customer = new Customer(Integer.parseInt(data.get(i)), data.get(j), Integer.parseInt(data.get(k)));
            customer.setParlour(parlour);
            customerQueue.add(customer);
        }
    }

    public void start() {
        Customer next;
        while ((next = customerQueue.poll()) != null) {
            Thread thread = new Thread(next);
            thread.start();
        }
        System.out.println(parlour.getLog());
    }




}
