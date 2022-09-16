import org.w3c.dom.css.CSSFontFaceRule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * COMP2240 Assignment 2
 * File:   P1.java
 * Created: 16/09/2022
 * Author: Jaydon Cameron (C3329145)
 **/


public class P1 {

    private final FarmerFactory farmerFactory;

    public P1() {
        farmerFactory = new FarmerFactory();
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new IllegalArgumentException("Input file name as argument.");
        }

        try (final Scanner scanner = new Scanner(new File(args[0]))) {
            int n = 0;
            int s = 0;
            while (scanner.hasNext()) {
                if (scanner.hasNext("N=")) {
                    n = scanner.nextInt();
                } else if (scanner.hasNext("S=")) {
                    s = scanner.nextInt();
                }
            }
            (new P1()).start(n, s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(int nCount, int sCount) {
        final Bridge bridge = new Bridge();
        final ArrayList<Farmer> farmers = new ArrayList<>();
        for (int i = 0; i < nCount; i++) {
            farmers.add(farmerFactory.create('N', bridge));
        }
        for (int i = 0; i < sCount; i++) {
            farmers.add(farmerFactory.create('S', bridge));
        }
        for (brid)
    }
}
