package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class FactionCMD implements CommandExecutor {
    private SubCMD subfac = new SubCMD();

    public FactionCMD() {
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
            List<String> factions = db.getDatabaseFactions();
            if (args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("sel")) {
                clover.getLogger().info(factions.toString());
                boolean match = false;
                for (String s : factions) {
                    if (args[1].equalsIgnoreCase(s)) {
                        subfac.SubFactioncommand(sender, s);
                        match = true;
                    }
                }
                if (!match) {
                    sender.sendMessage(clover.cloverprefix + "Use /faction select [faction name] and pick one of the following factions:");
                    for (String s : factions) {
                        sender.sendMessage("» " + ChatColor.GOLD + s);
                    }
                }
            }
        } else {
            sender.sendMessage(clover.cloverprefix + ChatColor.RED + "Invalid input" + ChatColor.WHITE + " did you mean /faction select?");
        }
        return true;
    }
}
