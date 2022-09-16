import java.util.concurrent.Semaphore;

/**
 * COMP2240 Assignment 2
 * File:   FarmerFactory.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class FarmerFactory {

    private int northIdCount;
    private int southIdCount;

    public FarmerFactory() {
        northIdCount = 1;
        southIdCount = 1;
    }

    public Farmer create(char type, Bridge bridge) {
        if (type == 'N') {
            return new Farmer(bridge,"N_Farmer" + northIdCount++);
        } else if (type == 'S') {
            return new Farmer(bridge,"S_Farmer" + southIdCount++);
        } else {
            throw new IllegalArgumentException("Expected 'N' or 'S' but received " + type);
        }
    }
}
