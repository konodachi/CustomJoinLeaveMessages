package me.konodachi.customJoinLeaveMessages.Listeners;

import me.konodachi.customJoinLeaveMessages.Role;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class JoinListener implements Listener {
    Configuration config;
    List<Role> roles;

    public JoinListener(List<Role> rolesInput, Configuration configInput){
        updateConfig(rolesInput, configInput);
    }

    public void updateConfig(List<Role> rolesInput, Configuration configInput){
        this.roles = rolesInput;
        this.config = configInput;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()){
            String message = config.getString("first-time-message", "Welcome to %server-name%, %player-name%!")
                            .replace("%player-name%", player.getDisplayName())
                            .replace("%server-name%", config.getString("server-name", "PlaceHolderSMP"));
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
            return;
        }

        for (Role role: roles){
            if (!player.hasPermission(role.getPermission())) continue;
            String message = role.getJoinMessage()
                            .replace("%player-name%", player.getDisplayName())
                            .replace("%server-name%", config.getString("server-name", "PlaceHolderSMP"));
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
            return;
        }
    }
}
