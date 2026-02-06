package me.konodachi.customJoinLeaveMessages.Listeners;

import me.konodachi.customJoinLeaveMessages.CustomJoinLeaveMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;
import java.util.Set;

public class LeaveListener implements Listener {
    CustomJoinLeaveMessages plugin =  CustomJoinLeaveMessages.getInstance();

    Set<String> permissions =
            Objects.requireNonNull(
                    plugin.getConfig().getConfigurationSection("roles")).getKeys(false);

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();

        for (String role : permissions) {
            if (player.hasPermission(role)) {
                String message = plugin.getConfig()
                        .getConfigurationSection("roles." + role + ".leave-message").getString(role)
                        .replace("%player-name%", player.getDisplayName())
                        .replace("%server-name%", plugin.getConfig().getString("server-name"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                return;
            }
        }

    }
}
