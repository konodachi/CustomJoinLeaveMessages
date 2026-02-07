package me.konodachi.customJoinLeaveMessages.Listeners;

import me.konodachi.customJoinLeaveMessages.CustomJoinLeaveMessages;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class JoinListener implements Listener {
    CustomJoinLeaveMessages plugin;
    Set<String> permissions;
    Map<String, String> roleMessageDict =  new HashMap<>();

    public JoinListener(@NonNull CustomJoinLeaveMessages plugin) {
        this.plugin = plugin;
        reloadConfig(plugin);
    }

    public void reloadConfig(CustomJoinLeaveMessages plugin){
        permissions = Objects.requireNonNull(plugin.getConfig().getConfigurationSection("roles")).getKeys(false);
        roleMessageDict = new HashMap<>();
        // TODO: change placeholder message to an actual default message
        for (String role : permissions){
            roleMessageDict.put(role, plugin.getConfig().getString("roles." + role + ".join-message", "Placeholder Message"));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            String message = plugin.getConfig().getString("first-time-message", "Welcome to %server-name%, %player-name%!")
                    .replace("%server-name%", plugin.getConfig().getString("server-name", "PlaceHolderSMP"))
                    .replace("%player-name%", player.getName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
            return;
        }
        for (String role : permissions) {
            if (player.hasPermission(plugin.getConfig().getString("roles." + role + ".permission", "customjoinleave.default"))) {
                String message = plugin.getConfig()
                        .getString("roles." + role + ".join-message", "&aWelcome back, &4%player-name%&a!")
                        .replace("%player-name%", player.getDisplayName())
                        .replace("%server-name%", plugin.getConfig().getString("server-name", "PlaceHolderSMP"));
                event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
                return;
            }
        }

    }
}
