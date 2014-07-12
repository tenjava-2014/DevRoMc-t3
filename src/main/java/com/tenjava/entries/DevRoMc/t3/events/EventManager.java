package com.tenjava.entries.DevRoMc.t3.events;

import com.tenjava.entries.DevRoMc.t3.EventsCore;
import com.tenjava.entries.DevRoMc.t3.events.event.ChickenRain;
import com.tenjava.entries.DevRoMc.t3.utils.ColorUtil;
import com.tenjava.entries.DevRoMc.t3.utils.MathUtil;
import com.tenjava.entries.DevRoMc.t3.utils.MessagesUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.HandlerList;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright DevRo_ (c) 2014. All Rights Reserved.
 * Programmed by: DevRo_ (Erik Rosemberg)
 * Creation Date: 12, 07, 2014
 * Programmed for the DevRoMc-t3 project.
 */
public class EventManager {
    public List<BaseEvent> events = new ArrayList<BaseEvent>();

    private static EventManager instance;

    public static EventManager getInstance() {
        if(instance == null) {
            instance = new EventManager();
        }

        return instance;
    }

    public EventManager() {
        new BukkitRunnable() {
            @Override
            public void run() {
                startEvent(getRandomEvent());
            }
        }.runTaskTimer(EventsCore.getInstance(), 0L, 1200L);
    }

    public BaseEvent getRandomEvent() {
        return events.get(MathUtil.r(events.size()));
    }

    public void registerEvent(BaseEvent event) {
        events.add(event);
    }

    public void registerEvents(BaseEvent... e) {
        for (BaseEvent event : e) {
            registerEvent(event);
        }
    }

    public void startEvent(final BaseEvent event) {
        if (!event.isRunning) {
            EventsCore.getInstance().getServer().getPluginManager().registerEvents(event, EventsCore.getInstance());
            event.isRunning = true;
            event.onStart();

            MessagesUtil.broadcast("The event " + event.getName() + " has started!");

            new BukkitRunnable() {
                @Override
                public void run() {
                   endEvent(event);
                }
            }.runTaskLater(EventsCore.getInstance(), 600L);
        }
    }

    public void endEvent(BaseEvent event) {
        if (event.isRunning) {
            event.isRunning = false;
            event.onEnd();

            MessagesUtil.broadcast("The event " + event.getName() + " has ended. A new event will start in 30 Seconds!");
            HandlerList.unregisterAll(event);
        }
    }
}
