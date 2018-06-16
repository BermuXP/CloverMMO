package github.bermuda.clovermmo.commands;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

<<<<<<< HEAD
public class Rank implements CommandExecutor {
=======
>>>>>>> ee3b6ae04f8db72b997a9aa99316f848797b77ed

public class rank implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player to perform this command!");
<<<<<<< HEAD
            return false;
        }
        Player player = (Player) sender;
        player.sendMessage(ChatColor.AQUA + "" + vault.getGroupPrefix("world", "default") + "command executed " + player.getName() + "!");
        return true;
=======
            return true;
        }
        setupChat();
        Player player = (Player) sender;
        player.sendMessage(chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§"));
//        String s1="my name is khan my name is java";
//        String replaceString=s1.replace("is","was");//replaces all occurrences of "is" to "was"
//        NEED: replace("&", "§");
        return true;
    }

    public static Chat chat = null;

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
>>>>>>> ee3b6ae04f8db72b997a9aa99316f848797b77ed
    }
}

