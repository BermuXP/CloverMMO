package github.bermuda.clovermmo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class clovermmo implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(ChatColor.BOLD +""+ ChatColor.GREEN + "CloverMMO " + ChatColor.BLUE + "»" + ChatColor.GOLD + " is a MMORPG plugin ,with this you can implement new races, classes and many other MMO features!");
        return false;
    }
}
