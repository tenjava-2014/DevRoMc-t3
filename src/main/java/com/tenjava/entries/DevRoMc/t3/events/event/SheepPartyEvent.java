package com.tenjava.entries.DevRoMc.t3.events.event;

import com.tenjava.entries.DevRoMc.t3.EventsCore;
import com.tenjava.entries.DevRoMc.t3.events.BaseEvent;
import com.tenjava.entries.DevRoMc.t3.utils.ColorUtil;
import com.tenjava.entries.DevRoMc.t3.utils.FireworkUtils;
import com.tenjava.entries.DevRoMc.t3.utils.MathUtil;
import com.tenjava.entries.DevRoMc.t3.utils.NamesUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class SheepPartyEvent extends BaseEvent {
    @Override
    public void onStart() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            for (int i = 0; i < MathUtil.r(30); i++) {
                player.getWorld().spawn(player.getLocation(), Sheep.class);
            }
        }
        new BukkitRunnable() {
            @Override
            public void run() {
               if (isRunning) {
                   for (World w : EventsCore.getInstance().getServer().getWorlds()) {
                       for (Entity e : w.getEntitiesByClass(Sheep.class)){
                           Sheep s = (Sheep) e;

                           if (s.isAdult()) {
                               s.setBaby();
                           }

                           s.setCustomNameVisible(true);
                           s.setCustomName(ColorUtil.getRandomChatColor() + NamesUtil.getRandomName());

                           s.setVelocity(s.getLocation().toVector().subtract(s.getLocation().toVector()).multiply(1.0D).setY(0.2D).setZ(MathUtil.r(2)).setX(MathUtil.r(2)));

                           s.setColor(DyeColor.values()[MathUtil.r(DyeColor.values().length)]);

                           FireworkUtils.spawnFirework(s.getLocation(), FireworkUtils.getColor(MathUtil.r(17) + 1));
                       }
                   }
               }
            }
        }.runTaskTimer(EventsCore.getInstance(), 0L, 10L);
    }

    @Override
    public void onEnd() {
        for (World w : EventsCore.getInstance().getServer().getWorlds()) {
            for (Entity e : w.getEntitiesByClass(Sheep.class)) {
                e.remove();
            }
        }
    }

    @Override
    public String getName() {
        return "Sheep Party";
    }
}
