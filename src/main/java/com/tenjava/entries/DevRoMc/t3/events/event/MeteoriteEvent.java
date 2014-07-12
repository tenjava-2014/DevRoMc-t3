package com.tenjava.entries.DevRoMc.t3.events.event;

import com.tenjava.entries.DevRoMc.t3.EventsCore;
import com.tenjava.entries.DevRoMc.t3.events.BaseEvent;
import com.tenjava.entries.DevRoMc.t3.utils.EventUtils;
import com.tenjava.entries.DevRoMc.t3.utils.MathUtil;
import org.bukkit.*;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class MeteoriteEvent extends BaseEvent {
    @Override
    public void activate() {
        Location loc = EventUtils.getRandomLocation(EventsCore.getInstance().getServer().getWorlds().get(0));
        spawnMeteorite(loc);
    }

    private void spawnMeteorite(Location location) {
        EventUtils.createSphere(location, Material.OBSIDIAN, MathUtil.r(7));
        Bukkit.broadcastMessage(ChatColor.RED + "A meteor has fallen at " + location.getX() + ", " + location.getY() + ", " + location.getZ());
    }
}
