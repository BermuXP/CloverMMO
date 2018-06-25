package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class SubCommands {
    private CloverMMO plugin;
    private Database db;

    public SubCommands(CloverMMO cmmo) {
        this.plugin = cmmo;
    }

    public void RaceSubcommand(CommandSender sender, String[] args, String race) {
        Player player = (Player) sender;
        player.sendMessage(clover.cloverprefix + "You have successfully selected " + ChatColor.GOLD + race + ChatColor.WHITE + " as race!");
        dbloader();
        db.setRace(player, race);
    }

    public void ClassSelSubcommand(CommandSender sender, String[] args, String classes) {
        Player player = (Player) sender;
        player.sendMessage(clover.cloverprefix + "You have successfully selected " + ChatColor.GOLD + classes + ChatColor.WHITE + " as class!");
        dbloader();
        db.setClass(player, classes);
    }

    public void ClassSpecSubcommand(CommandSender sender, String[] args, String spec) {
        Player player = (Player) sender;
        player.sendMessage(clover.cloverprefix + "You have successfully selected " + ChatColor.GOLD + spec + ChatColor.WHITE + " as spec!");
        dbloader();
        db.setSpec(player, spec);
    }
    private void dbloader() {
        db = new SQLite(clover);
        db.load();
    }
}
