package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class race implements CommandExecutor {
    CloverMMO plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        String time = plugin.getConfig().getString("player-name");
        player.sendMessage(time);
//        plugin.getConfig().set("race.name", args);
        return true;
    }
}
