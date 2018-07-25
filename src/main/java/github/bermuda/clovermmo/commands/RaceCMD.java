package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class RaceCMD implements CommandExecutor {
    private SubCMD subrace = new SubCMD();

    public RaceCMD() {
        db = new SQLite(clover);
        db.load();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(clover.cloverprefix + "you must be a player to perform this command!");
            return false;
        }

        if (args.length == 2) {
            List<String> races = db.getDatabaseRaces();
            if (args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("sel")) {
                clover.getLogger().info(races.toString());
                boolean match = false;
                for (String s : races) {
                    if (args[1].equalsIgnoreCase(s)) {
                        subrace.RaceSubcommand(sender, args, s);
                        match = true;
                    }
                }
                if (!match) {
                    sender.sendMessage(clover.cloverprefix + "Use /race select [race name] and pick one of the following races:");
                    for (String s : races) {
                        sender.sendMessage("» " + ChatColor.GOLD + s);
                    }
                }
            }
        } else {
            sender.sendMessage(clover.cloverprefix + ChatColor.RED + "Invalid input" + ChatColor.WHITE + " did you mean /race select?");
        }
        return false;
    }
}
