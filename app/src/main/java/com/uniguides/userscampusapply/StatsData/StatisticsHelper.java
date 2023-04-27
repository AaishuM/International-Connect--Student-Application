package com.uniguides.userscampusapply.StatsData;

import java.util.Random;

public class StatisticsHelper {


        public static String[] getCountries() {
            return new String[] {"Country A", "Country B", "Country C", "Country D"};
        }


    private static final String[] COUNTRIES = {"USA", "Canada", "China", "India", "UK", "France", "Germany", "Japan", "South Korea", "Mexico"};

    // Returns an array of random integers between 0 and 100.
    public static int[] getRandomData() {
        Random random = new Random();
        int[] data = new int[COUNTRIES.length];
        for (int i = 0; i < COUNTRIES.length; i++) {
            data[i] = random.nextInt(101);
        }
        return data;
    }

    // Returns an array of random doubles between 0 and 4.
    public static double[] getRandomGPAs() {
        Random random = new Random();
        double[] data = new double[COUNTRIES.length];
        for (int i = 0; i < COUNTRIES.length; i++) {
            data[i] = random.nextDouble() * 4.0;
        }
        return data;
    }

    // Returns a random number of international students for last year and this year.
    public static int[] getRandomInternationalStudentsCount() {
        Random random = new Random();
        int[] data = new int[2];
        data[0] = random.nextInt(1001);
        data[1] = random.nextInt(1001);
        return data;
    }
}
