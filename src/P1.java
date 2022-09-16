import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            final Pattern nPattern = Pattern.compile("N=\\d+,?");
            final Pattern sPattern = Pattern.compile("S=\\d+,?");
            final Pattern valPattern = Pattern.compile("\\d+");
            while (scanner.hasNext()) {
                if (scanner.hasNext(nPattern)) {
                    final Matcher m = valPattern.matcher(scanner.next());
                    if (m.find()) {
                        n = Integer.parseInt(m.group());
                    } else {
                        // Shouldn't happen
                        throw new IOException();
                    }
                } else if (scanner.hasNext(sPattern)) {
                    final Matcher m = valPattern.matcher(scanner.next());
                    if (m.find()) {
                        s = Integer.parseInt(m.group());
                    } else {
                        // Shouldn't happen
                        throw new IOException();
                    }
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
        for (final Farmer farmer : farmers) {
            (new Thread(farmer)).start();
        }
    }
}
