package me.konodachi.customJoinLeaveMessages.Commands;

import me.konodachi.customJoinLeaveMessages.CustomJoinLeaveMessages;
import me.konodachi.customJoinLeaveMessages.Listeners.JoinListener;
import me.konodachi.customJoinLeaveMessages.Listeners.LeaveListener;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class ReloadConfigCommand implements CommandExecutor {
    CustomJoinLeaveMessages plugin;
    JoinListener joinListener;
    LeaveListener leaveListener;

    public ReloadConfigCommand(CustomJoinLeaveMessages plugin, JoinListener joinListener, LeaveListener leaveListener){
        this.plugin = plugin;
        this.joinListener = joinListener;
        this.leaveListener = leaveListener;
    }

    @Override
    public boolean onCommand(@NonNull CommandSender commandSender, @NonNull Command command, @NonNull String s, String @NonNull [] strings) {
        if (commandSender instanceof Player p){
            if (!p.hasPermission("reloadConfig")){
                return false;
            } else{
                plugin.loadConfig();
                joinListener.updateConfig(plugin.getRoles(), plugin.getConfiguration());
                leaveListener.updateConfig(plugin.getRoles(), plugin.getConfiguration());
                return true;
            }
        }

        return true;
    }
}
