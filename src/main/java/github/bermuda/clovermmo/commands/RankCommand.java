package github.bermuda.clovermmo.commands;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static org.bukkit.Bukkit.getServer;

public class RankCommand implements CommandExecutor {

    public static Chat chat = null;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player to perform this command!");
            return false;
        }
        setupChat();
        Player player = (Player) sender;
        player.sendMessage(clover.cloverprefix + chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§"));
        return true;
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }
}

