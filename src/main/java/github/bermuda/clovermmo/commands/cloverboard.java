package github.bermuda.clovermmo.commands;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scoreboard.*;

import static org.bukkit.Bukkit.getServer;

public class cloverboard implements CommandExecutor {

    public static Chat chat = null;

    //todo need to update when health is low, new rank/race etc
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("you must be a player to perform this command!");
            return false;
        }
        //loading scoreboarConfig
        ScoreboardManager scoreboard = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboard.getNewScoreboard();

        //scoreboards object
        Objective object = board.registerNewObjective("cloverboard", "");
        object.setDisplaySlot(DisplaySlot.SIDEBAR);
        object.setDisplayName(ChatColor.DARK_AQUA + "CloverMMO");

        setupChat();
        Player player = (Player) sender;

        Score fourteen = object.getScore(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "+--------------------+ ");
        fourteen.setScore(14);

        Score thirteen = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Player:");
        thirteen.setScore(13);
        Score twelve = object.getScore(chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§") + " " + player.getDisplayName());
        twelve.setScore(12);

        Score eleven = object.getScore(" ");
        eleven.setScore(11);

        Score ten = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Race:");
        ten.setScore(10);
        Score nine = object.getScore(ChatColor.WHITE + "Elf");
        nine.setScore(9);

        Score eight = object.getScore("  ");
        eight.setScore(8);

        Score seven = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Health:");
        seven.setScore(7);

        Score six = object.getScore((player.getHealth()-player.getLastDamage()) + "/" + player.getMaxHealth());
        six.setScore(6);

        Score five = object.getScore(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "+--------------------+");
        five.setScore(5);
//        for (Player player1 : Bukkit.getOnlinePlayers()) {
//
//        }
        player.setScoreboard(board);
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
