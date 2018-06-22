package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static org.bukkit.Bukkit.getServer;

public class ProfileCommand implements CommandExecutor {
    private Database db;
    public static Chat chat = null;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        this.db = new SQLite(clover);
        this.db.load();
        setupChat();

        Boolean races = clover.getConfig().getBoolean("profile.race");
        Boolean rank = clover.getConfig().getBoolean("profile.seperaterank");
        Boolean username = clover.getConfig().getBoolean("profile.seperateusername");
        Boolean usernamerank = clover.getConfig().getBoolean("profile.rankandusername");
        Boolean level = clover.getConfig().getBoolean("profile.level");
        Boolean exp = clover.getConfig().getBoolean("profile.exp");
        Boolean maxhp = clover.getConfig().getBoolean("profile.maxhp");

        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Profile");
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "+-------------------------------+");
        if (rank == true) {
            sender.sendMessage("» "+ ChatColor.GOLD + "Rank: " + chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§"));
        }
        if (username == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Name: " + player.getDisplayName());
        }
        if (usernamerank == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Player: " + chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§") + " " + player.getDisplayName());
        }
        if (races == true) {
            String race = this.db.getRace(player);
            sender.sendMessage("» " + ChatColor.GOLD +"Race: " + ChatColor.WHITE + race);
        }
        if (races == true) {
            String classes = this.db.getClasses(player);
            sender.sendMessage("» " + ChatColor.GOLD +"Class: " + ChatColor.WHITE + classes);
        }
        if (level == true) {
            sender.sendMessage("» " + ChatColor.GOLD +"Level: " + ChatColor.WHITE + player.getLevel());
        }
        if (exp == true) {
            sender.sendMessage("» " + ChatColor.GOLD +"Exp: " + ChatColor.WHITE + player.getExp() + "/" + player.getExpToLevel());
        }
        if (maxhp == true) {
            sender.sendMessage("» " + ChatColor.GOLD +"Max Health: " + ChatColor.WHITE + String.valueOf(player.getHealthScale()));
        }
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "+-------------------------------+");
        return false;
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
