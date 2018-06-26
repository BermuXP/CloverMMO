package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class ClassCommand implements CommandExecutor {
    private CloverMMO clover;
    private Database db;
    private SubCommands subclass;

    public ClassCommand(CloverMMO cmmo) {
        subclass = new SubCommands(cmmo);
        this.clover = cmmo;
        db = new SQLite(clover);
        db.load();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            List<String> dclasses = db.getDatabaseClasses();
            if (args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("sel") ) {
//                List<String> dclasses = db.getDatabaseClasses();
                this.clover.getLogger().info(dclasses.toString());
                boolean match = false;
                for (String s : dclasses) {
                    if (args[1].equalsIgnoreCase(s)) {
                        subclass.ClassSelSubcommand(sender, args, s);
                        match = true;
                    }
                }
                if (!match) {
                    sender.sendMessage(clover.cloverprefix + "No such class exists, select one of the follow classes:");
                    for (String s : dclasses) {
                        sender.sendMessage("» " + ChatColor.GOLD + s);
                    }
                }
            }
            List<String> spec = clover.getConfig().getStringList("classes." + dclasses + ".spec.name");
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
