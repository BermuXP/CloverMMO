package github.bermuda.clovermmo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class ClovermmoCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args.length) {
            case 0:
                sender.sendMessage(clover.cloverprefix + "is a MMORPG plugin, with this you can implement new races, classes and many other MMO features!");
                return true;
            case 1:
                if (args[0].equalsIgnoreCase("?") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("commands") || args[0].equalsIgnoreCase(("info"))) {
                    sender.sendMessage(clover.cloverprefix + "You executed the CloverMMO help command");
                } else {
                    sender.sendMessage(clover.cloverprefix + "is a MMORPG plugin, with this you can implement new races, classes and many other MMO features!");
                }
                return true;
            default:
                return false;
        }
    }
}

