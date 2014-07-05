package com.tenjava.entries.DevRoMc.t3;

import org.bukkit.plugin.java.JavaPlugin;

public class TenJava extends JavaPlugin {
    private static TenJava instance;

    @Override
    public void onEnable() {
        setInstance(this);
    }

    @Override
    public void onDisable() {
        setInstance(null);
    }

    public static void setInstance(TenJava instance) {
        TenJava.instance = instance;
    }

    public static TenJava getInstance() {
        return instance;
    }
}
