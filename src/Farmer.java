/*
 * COMP2240 Assignment 2
 * File:    Farmer.java
 * Created: 13/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

/**
 * Represents a farmer in the 'sharing the bridge' problem
 *
 * @see Bridge
 * @see FarmerFactory
 */
public class Farmer implements Runnable {
    private final String id;
    private final Bridge bridge;
    private Bound bound;

    /**
     * Constructor that takes and sets the bridge and ID of the instantiated {@link Farmer}
     *
     * @param bridge the {@link Bridge} to be crossed by the {@link Farmer}
     * @param id     the ID of the {@link Farmer} (e.g., <code>N_Farmer1</code>, <code>N_Farmer2</code>, etc.)
     */
    public Farmer(Bridge bridge, String id) {
        this.id = id;
        this.bridge = bridge;
    }

    /**
     * Get the ID of this {@link Farmer}
     *
     * @return the ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the {@link Bound} enum that represents the cardinal direction that {@link Farmer} the farmer will travel
     *
     * @return the {@link Bound}
     */
    public Bound getBound() {
        return bound;
    }

    /**
     * Set the {@link Bound} of this {@link Farmer}
     *
     * @param bound the {@link Bound} enum representing the cardinal direction that this {@link Farmer} will travel
     */
    public void setBound(Bound bound) {
        this.bound = bound;
    }

    /**
     * Run this {@link Farmer} process
     * <p>Queues at the {@link Bridge} and crosses when it's their turn</p>
     */
    @Override
    public void run() {
        while (!bridge.exit()) {
            try {
                System.out.printf("%s:\tWaiting for bridge. Going towards %s\n", getId(), getBound().getLabel());
                // Wait for turn
                bridge.getLock().acquire();
                try {
                    if (!bridge.exit()) {
                        // Cross bridge
                        bridge.cross(this);
                        if (bound.equals(Bound.N)) bound = Bound.S;
                        else bound = Bound.N;
                    }
                } finally {
                    bridge.getLock().release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Enum representing the islands that a {@link Farmer} is bound for
     */
    public enum Bound {
        N("North"), S("South");

        private final String label;

        Bound(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}
