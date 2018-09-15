package com.whackedblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WhackedPlugin extends JavaPlugin {

    static WhackedPlugin instance;
    static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();
        Bukkit.getLogger().info("Super plugin wlaczony!");
        getCommand("tokenize").setExecutor(new TokenizeCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Super plugin wylaczony!");
    }

}
