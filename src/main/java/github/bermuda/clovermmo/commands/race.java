package github.bermuda.clovermmo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import static github.bermuda.clovermmo.CloverMMO.clover;

public class race implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        clover.getConfig().get("player-name");
        clover.saveConfig();
        return true;
    }
}
