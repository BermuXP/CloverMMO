package github.bermuda.clovermmo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import github.bermuda.clovermmo.API.Vault;

public class rank implements CommandExecutor {

    public static Vault vault;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player to perform this command!");
            return false;
        }
            Player player = (Player) sender;
        try {
            player.sendMessage(ChatColor.AQUA + "" + vault.getPrimaryGroup("world", "default") + "command executed " + player.getName() + "!");
        } catch(CommandException e){
            e.printStackTrace();
        }
            return true;
    }
}

