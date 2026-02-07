package me.konodachi.customJoinLeaveMessages.Listeners;

import me.konodachi.customJoinLeaveMessages.CustomJoinLeaveMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class LeaveListener implements Listener {
    CustomJoinLeaveMessages plugin;
    Set<String> permissions;
    Map<String, String> roleMessageDict;

    public LeaveListener(@NonNull CustomJoinLeaveMessages plugin) {
        this.plugin = plugin;
        reloadConfig(plugin);
    }

    // TODO: Make a proper default message
    public void reloadConfig(CustomJoinLeaveMessages plugin) {
        permissions = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("roles")).getKeys(false);
        roleMessageDict = new HashMap<>();
        for (String role : permissions) {
            roleMessageDict.put(role,  plugin.getConfig().getString("roles." + role + ".leave-message", "Placeholder Message"));
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();

        for (String role : permissions) {
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
