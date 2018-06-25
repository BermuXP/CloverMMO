package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ClassCommand implements CommandExecutor {
    private CloverMMO clover;
    private SubCommands subclass;

    public ClassCommand(CloverMMO cmmo) {
        subclass = new SubCommands(cmmo);
        this.clover = cmmo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {

            if (args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("sel") ) {
                List<String> classes = clover.getConfig().getStringList("classes.");
                boolean match = false;
                for (String s : classes) {
                    if (args[1].equalsIgnoreCase(s)) {
                        subclass.ClassSelSubcommand(sender, args, s);
                        match = true;
                    }
                }
                if (!match) {
                    sender.sendMessage(clover.cloverprefix + "No such class exists, select one of the follow classes:");
                    for (String s : classes) {
                        sender.sendMessage("» " + ChatColor.GOLD + s);
                    }
                }
            }
            List<String> spec = clover.getConfig().getStringList("specs");
            if (args[0].equalsIgnoreCase("spec") || args[0].equalsIgnoreCase("subclass")) {
                boolean match = false;
                for (String specs : spec) {
                    if (args[1].equalsIgnoreCase(specs)) {
                        subclass.ClassSpecSubcommand(sender, args, specs);
                        match = true;
                    }
                }
                if (!match) {
                    sender.sendMessage(clover.cloverprefix + "No such spec exists, select one of the follow specs:");
                    for (String specs : spec) {
                        sender.sendMessage("» " + ChatColor.GOLD + specs);
                    }
                }
            }
        } else {
            sender.sendMessage(clover.cloverprefix + ChatColor.RED + "Invalid input" + ChatColor.WHITE + " did you mean /class spec or /class select?");
        }
        return false;
    }
}
