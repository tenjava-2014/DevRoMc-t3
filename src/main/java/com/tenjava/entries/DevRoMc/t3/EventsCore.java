package com.tenjava.entries.DevRoMc.t3;

import com.tenjava.entries.DevRoMc.t3.events.EventManager;
import com.tenjava.entries.DevRoMc.t3.events.event.*;
import org.bukkit.plugin.java.JavaPlugin;

public class EventsCore extends JavaPlugin {
    private static EventsCore instance;

    @Override
    public void onEnable() {
        setInstance(this);

        EventManager.getInstance();
        EventManager.getInstance().registerEvents(
                new WildfireEvent(),
                new MeteoriteEvent()
        );
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    public static void setInstance(EventsCore instance) {
        EventsCore.instance = instance;
    }

    public static EventsCore getInstance() {
        return instance;
    }
}
