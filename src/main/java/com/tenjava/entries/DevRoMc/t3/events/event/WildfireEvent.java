package com.tenjava.entries.DevRoMc.t3.events.event;

import com.tenjava.entries.DevRoMc.t3.EventsCore;
import com.tenjava.entries.DevRoMc.t3.events.BaseEvent;
import com.tenjava.entries.DevRoMc.t3.utils.EventUtils;
import com.tenjava.entries.DevRoMc.t3.utils.MathUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class WildfireEvent extends BaseEvent {
    @Override
    public void activate() {
        Location location = EventUtils.getRandomLocation(EventsCore.getInstance().getServer().getWorlds().get(0));

        EventUtils.makeWildfire(location, MathUtil.r(30));

        Bukkit.broadcastMessage(ChatColor.RED + "A wildfire has started at " + location.getX() + ", " + location.getY() + ", " + location.getZ());
    }
}
