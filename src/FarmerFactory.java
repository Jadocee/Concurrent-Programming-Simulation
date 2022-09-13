/**
 * COMP2240 Assignment 2
 * File:   FarmerFactory.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class FarmerFactory {

    private static final FarmerFactory INSTANCE = new FarmerFactory();
    private int northIdCount;
    private int southIdCount;

    private FarmerFactory() {
        northIdCount = 1;
        southIdCount = 1;
    }

    public static FarmerFactory getInstance() {
        return INSTANCE;
    }

    public Farmer create(char type) {
        if (type == 'N') {
            return new Farmer("N_Farmer" + northIdCount++);
        } else if (type == 'S') {
            return new Farmer("S_Farmer" + southIdCount++);
        } else {
            throw new IllegalArgumentException("Expected 'N' or 'S' but received " + type);
        }
    }
}
