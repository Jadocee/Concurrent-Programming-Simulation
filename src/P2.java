import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 */
public class P2 {

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Input file name as argument.");
        }
        final List<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(args[0]))) {
            String line;
            while (!(line = scanner.nextLine()).equals("END")) {
                data.addAll(List.of(line.split("\\s+")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        final ParlourSimulation simulation = new ParlourSimulation();
        simulation.loadData(data);
        simulation.start();
    }

}
