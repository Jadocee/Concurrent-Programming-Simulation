/*
 * COMP2240 Assignment 2
 * File:   FarmerFactory.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 */

/**
 * Implementation of the factory design pattern, used to create instances of {@link Farmer}
 */
public class FarmerFactory {

    private int northIdCount;
    private int southIdCount;

    /**
     * Default constructor
     */
    public FarmerFactory() {
        northIdCount = southIdCount = 0;
    }

    /**
     * Creates a new {@link Farmer} instance
     *
     * @param type   the type of the {@link Farmer} (the cardinal direction their heading in)
     * @param bridge the {@link Bridge} the {@link Farmer} will cross
     * @return the {@link Farmer}
     */
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
