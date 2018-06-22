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

    public void RaceSubcommand (CommandSender sender, String[] args, String race) {
        Player player = (Player) sender;
        player.sendMessage(clover.cloverprefix + "You have selected " + ChatColor.GOLD + race + ChatColor.WHITE +" as race!");
        db = new SQLite(clover);
        db.load();
        db.setRace(player, race, 6);
    }
}