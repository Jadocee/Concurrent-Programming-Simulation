/*
 * COMP2240 Assignment 2
 * File:    P3.java
 * Created: 20/09/2022
 * Author:  Jaydon Cameron (C3329145)
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 *
 */
public class P3 extends ParlourSimulation {
    public P3() {
        super(new MonitorTimer(), new MonitorParlour());
    }

    public static void main(String[] args) {
        if (args.length < 1) throw new IllegalArgumentException("Input file name as argument.");
        try {
            final List<String> data = ParlourSimulation.readInData(new File(args[0]));
            final P3 p3 = new P3();
            p3.loadData(data);
            p3.start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
