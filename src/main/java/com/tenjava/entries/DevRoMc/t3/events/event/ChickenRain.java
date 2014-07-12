package com.tenjava.entries.DevRoMc.t3.events.event;

import com.tenjava.entries.DevRoMc.t3.EventsCore;
import com.tenjava.entries.DevRoMc.t3.events.BaseEvent;
import com.tenjava.entries.DevRoMc.t3.utils.ColorUtil;
import com.tenjava.entries.DevRoMc.t3.utils.FireworkUtils;
import com.tenjava.entries.DevRoMc.t3.utils.MathUtil;
import com.tenjava.entries.DevRoMc.t3.utils.NamesUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class ChickenRain extends BaseEvent {

    public void onStart() {
        int i = MathUtil.r(30);

        for (Player player : EventsCore.getInstance().getServer().getOnlinePlayers()) {
            for (int chicken = 0; chicken < i; chicken++) {
                World world = player.getWorld();

                Location loc = player.getLocation().clone();
                loc.setY(player.getLocation().getY() + MathUtil.r(3));
                loc.setX(player.getLocation().getX() + MathUtil.r(6));
                loc.setZ(player.getLocation().getZ() + MathUtil.r(6));

                Chicken c = world.spawn(loc, Chicken.class);

                if (c.isAdult()) {
                    c.setBaby();
                    c.setAgeLock(true);
                }

                c.setVelocity(c.getLocation().toVector().subtract(c.getLocation().toVector()).multiply(2.0D).setY(MathUtil.r(2)).setZ(MathUtil.r(3)).setX(MathUtil.r(3)));

                c.setCustomNameVisible(true);
                c.setCustomName(ColorUtil.getRandomChatColor() + NamesUtil.getRandomName());

                FireworkUtils.playFirework(c.getLocation(), FireworkUtils.getColor(MathUtil.r(17) + 1));
            }
        }
    }

    public void onEnd() {
        for (Player player : EventsCore.getInstance().getServer().getOnlinePlayers()) {
            World world = player.getWorld();

            for (Chicken chicken : world.getEntitiesByClass(Chicken.class)) {
                chicken.remove();
            }
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().startsWith("/killall")) {
            for (Entity entity : event.getPlayer().getWorld().getEntities())  {
                if (!(entity instanceof HumanEntity)) {
                    entity.remove();
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Chicken Rain";
    }
}
