package com.tenjava.entries.DevRoMc.t3.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class MessagesUtil {
    public static void broadcast(String message) {
        Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ColorUtil.getRandomChatColor()  + "Events" + ChatColor.GRAY + "] " + ChatColor.YELLOW + message);
    }
}
