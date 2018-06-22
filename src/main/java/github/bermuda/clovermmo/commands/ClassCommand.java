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
        List<String> classes = clover.getConfig().getStringList("classes");
        if (args.length == 1) {
            boolean match = false;
            for (String s : classes) {
                if (args[0].equalsIgnoreCase(s)) {
                    subclass.ClassSubcommand(sender, args, s);
                    match = true;
                }
            }
            if (!match) {
                sender.sendMessage(clover.cloverprefix + "No such class exists, select one of the follow classes:");
                for (String s : classes) {
                    sender.sendMessage("» " + ChatColor.GOLD + s);
                }
            }
        } else {
            sender.sendMessage(clover.cloverprefix + "Use /class [classname] and pick one of the following classes:");
            for (String s : classes) {
                sender.sendMessage("» " + ChatColor.GOLD + s);
            }
        }
        return false;
    }
}
