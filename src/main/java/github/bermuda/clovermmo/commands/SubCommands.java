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
        player.sendMessage(clover.cloverprefix + "You have succesfully selected " + ChatColor.GOLD + race + ChatColor.WHITE + " as race!");
        db = new SQLite(clover);
        db.load();
        db.setRace(player, race);
    }

    public void ClassSubcommand(CommandSender sender, String[] args, String classes) {
        Player player = (Player) sender;
        player.sendMessage(clover.cloverprefix + "You have succesfully selected " + ChatColor.GOLD + classes + ChatColor.WHITE + " as race!");
        db = new SQLite(clover);
        db.load();
        db.setClass(player, classes);
    }

}
