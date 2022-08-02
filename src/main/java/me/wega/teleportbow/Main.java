package me.wega.teleportbow;

import me.wega.teleportbow.commands.GiveBowCommand;
import me.wega.teleportbow.listeners.BowListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();


        Objects.requireNonNull(getCommand("givebow")).setExecutor(new GiveBowCommand());
        getServer().getPluginManager().registerEvents(new BowListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
