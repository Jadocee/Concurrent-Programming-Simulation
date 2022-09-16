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
        northIdCount = southIdCount = 0;
    }

    public Farmer create(char type, Bridge bridge) {
        switch (type) {
            case 'N': {
                final Farmer farmer = new Farmer(bridge, "N_Farmer" + ++northIdCount);
                farmer.setBound(Farmer.Bound.S);
                return farmer;
            }
            case 'S': {
                final Farmer farmer = new Farmer(bridge, "S_Farmer" + ++southIdCount);
                farmer.setBound(Farmer.Bound.N);
                return farmer;
            }
            default:
                throw new IllegalArgumentException("Expected 'N' or 'S' but received " + type);
        }
    }
}
