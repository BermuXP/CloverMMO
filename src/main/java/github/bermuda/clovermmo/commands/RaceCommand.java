package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class RaceCommand implements CommandExecutor {
    private CloverMMO clover;
    private SubCommands subrace;

    public RaceCommand(CloverMMO cmmo) {
        subrace = new SubCommands(cmmo);
        this.clover = cmmo;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> races = clover.getConfig().getStringList("races");
        if (args.length == 1) {
            boolean match = false;
            for (String s : races) {
                if (args[0].equalsIgnoreCase(s)) {
                    subrace.RaceSubcommand(sender, args, s);
                    match = true;
                }
            }
            if (!match) {
                sender.sendMessage(clover.cloverprefix + "No such race exists, select one of the follow races:");
                for (String s : races) {
                    sender.sendMessage("» " + ChatColor.GOLD  + s);
                }
            }
        } else {
            sender.sendMessage(clover.cloverprefix + "Use /race [racename] and pick one of the following races:");
            for (String s : races) {
                sender.sendMessage(ChatColor.GOLD + "» " + s);
            }
        }
        return false;
    }


}
