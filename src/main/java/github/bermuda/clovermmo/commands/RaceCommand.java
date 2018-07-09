package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class RaceCommand implements CommandExecutor {
    private CloverMMO clover;
    private Database db;
    private SubCommands subrace;

    public RaceCommand(CloverMMO cmmo) {
        subrace = new SubCommands(cmmo);
        this.clover = cmmo;
        db = new SQLite(clover);
        db.load();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

                if (args.length == 2) {
                    List<String> races = db.getDatabaseRaces();
                    if (args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("sel")) {
                        this.clover.getLogger().info(races.toString());
                        boolean match = false;
                        for (String s : races) {
                            if (args[1].equalsIgnoreCase(s)) {
                                subrace.RaceSubcommand(sender, args, s);
                                match = true;
                            }
                        }
                        if (!match) {
                            sender.sendMessage(clover.cloverprefix + "Use /race select [racename] and pick one of the following races:");
                            for (String s : races) {
                                sender.sendMessage("» " + ChatColor.GOLD + s);
                            }
                        }
                    }
                } else {
                    sender.sendMessage(clover.cloverprefix + ChatColor.RED + "Invalid input" + ChatColor.WHITE + " did you mean /race select?");
                }
        return true;
    }
}
