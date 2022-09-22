/*
 * COMP2240 Assignment 2
 * File:    P2.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 */
public class P2 extends ParlourSimulation {

    public P2() {
        super(new SemaphoreTimer(), new SemaphoreParlour());
    }

    public static void main(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("Input file name as argument.");
        try {
            final List<String> data = ParlourSimulation.readInData(new File(args[0]));
            final P2 p2 = new P2();
            p2.loadData(data);
            p2.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
