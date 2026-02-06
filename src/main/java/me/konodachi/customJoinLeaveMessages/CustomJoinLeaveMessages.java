package me.konodachi.customJoinLeaveMessages;

import me.konodachi.customJoinLeaveMessages.Listeners.JoinListener;
import me.konodachi.customJoinLeaveMessages.Listeners.LeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinLeaveMessages extends JavaPlugin {
    private static CustomJoinLeaveMessages instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static CustomJoinLeaveMessages getInstance() {
        return instance;
    }
}
