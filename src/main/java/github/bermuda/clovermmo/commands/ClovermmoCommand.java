package github.bermuda.clovermmo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class ClovermmoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage(clover.cloverprefix + "is a MMORPG plugin, with this you can implement new races, classes and many other MMO features!");
        return false;
    }
}
