package me.konodachi.customJoinLeaveMessages;

import me.konodachi.customJoinLeaveMessages.Listeners.JoinListener;
import me.konodachi.customJoinLeaveMessages.Listeners.LeaveListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class CustomJoinLeaveMessages extends JavaPlugin {
    Set<String> roleKeys;
    Set<Role> roles;

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

    public void loadConfig(){
        roles = new HashSet<>();

        try{
            ConfigurationSection rolesSection = Objects.requireNonNull(getConfig().getConfigurationSection("roles"));
            roleKeys = rolesSection.getKeys(false);
            if (roleKeys.isEmpty()){
                getLogger().warning("No roles defined. Falling back to defaults.");
            }
        } catch (NullPointerException exception){
            getLogger().warning("Invalid configuration. " +
                    "Please refer to github.com/konodachi/CustomJoinLeaveMessages");
        }

        for (String key: roleKeys){
            Role temp = new Role();
            temp.setName(key);
            temp.setPermission(getConfig().getString("roles." + temp.getName() + ".permission", "customjoinleave.default"));
            temp.setJoinMessage(getConfig().getString("roles." + temp.getName() + ".join-message", "No join message set for this role."));
            temp.setLeaveMessage(getConfig().getString("roles." + temp.getName() + ".leave-message", "No leave message set for this role."));
        }
    }
}
