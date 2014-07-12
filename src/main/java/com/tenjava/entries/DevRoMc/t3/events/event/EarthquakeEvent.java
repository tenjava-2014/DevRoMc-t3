package com.tenjava.entries.DevRoMc.t3.events.event;

import com.tenjava.entries.DevRoMc.t3.EventsCore;
import com.tenjava.entries.DevRoMc.t3.events.BaseEvent;
import com.tenjava.entries.DevRoMc.t3.utils.EventUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class EarthquakeEvent extends BaseEvent {
    @Override
    public void activate() {
        Location loc = EventUtils.getRandomLocation(EventsCore.getInstance().getServer().getWorlds().get(0));

        EventUtils.createSpace(loc);

        Bukkit.broadcastMessage(ChatColor.RED + "There was an earthquake at " + loc.getX() + ", " + loc.getY() + ", " +loc.getZ());
    }
}
