package me.konodachi.customJoinLeaveMessages;

import me.konodachi.customJoinLeaveMessages.Listeners.JoinListener;
import me.konodachi.customJoinLeaveMessages.Listeners.LeaveListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomJoinLeaveMessages extends JavaPlugin {

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
