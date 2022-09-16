import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Island implements Runnable {

    private final List<Farmer> farmers;

    public Island() {
        this.farmers = new ArrayList<>();
    }

    public void addFarmer(Farmer farmer) {
        farmers.add(farmer);
    }

    @Override
    public void run() {

    }
}
