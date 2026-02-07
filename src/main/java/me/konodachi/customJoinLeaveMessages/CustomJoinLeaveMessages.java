package me.konodachi.customJoinLeaveMessages;

import me.konodachi.customJoinLeaveMessages.Listeners.JoinListener;
import me.konodachi.customJoinLeaveMessages.Listeners.LeaveListener;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class CustomJoinLeaveMessages extends JavaPlugin {
    Set<String> roleKeys;
    List<Role> roles;
    Configuration config;
    Configuration defaultConfig;

    @Override
    public void onEnable() {

        saveDefaultConfig();
        loadConfig();

        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new LeaveListener(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig(){
        roles = new ArrayList<>();
        config = getConfig();
        defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(getResource("config.yml")));

        try{
            ConfigurationSection rolesSection = Objects.requireNonNull(getConfig().getConfigurationSection("roles"));
            roleKeys = rolesSection.getKeys(false);
            if (roleKeys.isEmpty()){
                getLogger().warning("No roles defined. Falling back to defaults.");
                config = defaultConfig;
                roleKeys = config.getConfigurationSection("roles").getKeys(false);
            }
        } catch (NullPointerException exception){
            getLogger().warning("Invalid configuration. " +
                    "Please refer to github.com/konodachi/CustomJoinLeaveMessages");
            config = defaultConfig;
            roleKeys = config.getConfigurationSection("roles").getKeys(false);
        }

        for (String key: roleKeys){
            Role temp = new Role();
            temp.setName(key);
            temp.setPermission(config.getString("roles." + temp.getName() + ".permission", "customjoinleave.default"));
            temp.setJoinMessage(config.getString("roles." + temp.getName() + ".join-message", "No join message set for this role."));
            temp.setLeaveMessage(config.getString("roles." + temp.getName() + ".leave-message", "No leave message set for this role."));
            temp.setPriority(config.getInt("roles." + temp.getName() + ".priority"));
            roles.add(temp);
        }

        roles.sort(Role::compareTo);
    }
}
