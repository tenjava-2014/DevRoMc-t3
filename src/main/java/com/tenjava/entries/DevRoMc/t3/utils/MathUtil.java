package com.tenjava.entries.DevRoMc.t3.utils;

import java.util.Random;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class MathUtil {
    private static Random random = new Random();

    public static Random getRandom() {
        return random;
    }

    public static int r(int a) {
        return random.nextInt(a);
    }

    public static boolean hasFinished(long from, long required) {
        return System.currentTimeMillis() - from > required;
    }
}
