package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;

import github.bermuda.clovermmo.attributes.ClassAbilities;
import github.bermuda.clovermmo.config.setconfig.ProfileConfig;
import github.bermuda.clovermmo.database.SQLite;
import github.bermuda.clovermmo.API.placeholder.Placeholder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.color;
import static github.bermuda.clovermmo.CloverMMO.db;

public class ProfileCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(clover.cloverprefix + "you must be a player to perform this command!");
            return false;
        }

        Player player = (Player) sender;
        db.getUserData(player);
        List<String> text = ProfileConfig.profile().getProfileChatDisplay();

        for (String t : text) {
            String d = String.valueOf(Placeholder.onPlaceholderRequest(player, t));
            player.sendMessage(color(d));
        }
        return true;
    }
}
