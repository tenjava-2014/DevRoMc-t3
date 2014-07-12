package com.tenjava.entries.DevRoMc.t3.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class ColorUtil {
    private static ChatColor[] colors = ChatColor.values();

    public static ChatColor getRandomChatColor() {
        return colors[MathUtil.r(colors.length)];
    }
}
