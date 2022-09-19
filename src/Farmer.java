/**
 * COMP2240 Assignment 2
 * File:   Farmer.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Farmer implements Runnable {
    private final String id;
    private final Bridge bridge;
    private Bound bound;

    public Farmer(Bridge bridge, String id) {
        this.id = id;
        this.bridge = bridge;
    }

    public String getId() {
        return id;
    }

    public Bound getBound() {
        return bound;
    }

    public void setBound(Bound bound) {
        this.bound = bound;
    }

    @Override
    public void run() {
        while (!bridge.exit()) {
            try {
                System.out.printf("%s:\tWaiting for bridge. Going towards %s\n", getId(), getBound().getLabel());
                bridge.getLock().acquire();
                try {
                    if (!bridge.exit()) {
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
