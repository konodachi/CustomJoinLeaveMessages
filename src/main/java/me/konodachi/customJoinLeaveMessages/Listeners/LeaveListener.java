package me.konodachi.customJoinLeaveMessages.Listeners;

import me.konodachi.customJoinLeaveMessages.CustomJoinLeaveMessages;
import me.konodachi.customJoinLeaveMessages.Role;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jspecify.annotations.NonNull;

import java.util.Objects;
import java.util.Set;

public class LeaveListener implements Listener {
    CustomJoinLeaveMessages plugin;
    Set<String> roleKeys;
    Set<Role> roles;

    public LeaveListener(@NonNull CustomJoinLeaveMessages plugin) {
        this.plugin = plugin;
        reloadConfig(plugin);
    }

    // TODO: Make a proper default message
    public void reloadConfig(CustomJoinLeaveMessages plugin) {
        roleKeys = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("roles")).getKeys(false);

        for (String key: roleKeys){

        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();

        for (String role : roleKeys) {
            if (player.hasPermission(plugin.getConfig().getString("roles." + role + ".permission", "customjoinleave.default"))) {
                String message = plugin.getConfig()
                        .getString("roles." + role + ".leave-message", "")
                        .replace("%player-name%", player.getDisplayName())
                        .replace("%server-name%", plugin.getConfig().getString("server-name", "PlaceHolderSMP"));
                event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message));
                return;
            }
        }

    }
}
