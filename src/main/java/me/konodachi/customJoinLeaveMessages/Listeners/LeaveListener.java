package me.konodachi.customJoinLeaveMessages.Listeners;

import me.konodachi.customJoinLeaveMessages.Role;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class LeaveListener implements Listener {
    Configuration config;
    List<Role> roles;

    public LeaveListener(List<Role> rolesInput, Configuration configInput){
        updateConfig(rolesInput, configInput);
    }

    public void updateConfig(List<Role> rolesInput, Configuration configInput){
        this.roles = rolesInput;
        this.config = configInput;
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event){
        Player player = event.getPlayer();

        for (Role role: roles){
            if (!player.hasPermission(role.getPermission())) continue;
            String message = role.getLeaveMessage()
                    .replace("%player-name%", player.getDisplayName())
                    .replace("%server-name%", config.getString("server-name", "PlaceHolderSMP"));
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message));
            return;
        }
    }
}
