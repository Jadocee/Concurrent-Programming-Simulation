import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * COMP2240 Assignment 2
 * File:   Island.java
 * Created: 13/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class Island extends Thread {
    private Queue<Farmer> farmers;

    public Island() {
        this.farmers = new LinkedList<>();
    }
    public Island(List<Farmer> farmers) {
        this.farmers = new LinkedList<>();
        this.farmers.addAll(farmers);
    }

    @Override
    public void run() {
        for (final Farmer farmer : farmers) {
            farmer.start();
        }
    }
}
