package com.tenjava.entries.DevRoMc.t3.utils;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class NamesUtil {
    private static String[] names = {"Rosalina", "Shirley", "Ivy", "Phyliss", "Albina", "Jaime", "Rico", "Harvey", "Eddard", "Robert", "Tory", "Emerson", "Eugene", "Dale", "Forrest", "Jack", "Clint", "Felton"};

    public static String getRandomName() {
        return names[MathUtil.r(names.length)];
    }
}
