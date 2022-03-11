package bermuda.commands;

import bermuda.config.DefaultConfig;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

@CommandAlias("cloverboard|cboard")
public class CloverBoardCommand extends BaseCommand {

    @Subcommand("close|c")
    public void closeCloverBoard(CommandSender sender) {
        sender.sendMessage(DefaultConfig.getInstance().getPluginPrefix() + "Cloverboard closed");
        ScoreboardManager scoreboard = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboard.getNewScoreboard();
        scoreboard.getNewScoreboard();
        Player player = (Player) sender;
        player.setScoreboard(board);
    }

    @Default
    @Subcommand("show|s")
    public void loadCloverBoard(CommandSender sender) {
        //loading scoreboardConfig
        ScoreboardManager scoreboard = Bukkit.getScoreboardManager();
        Scoreboard board = scoreboard.getNewScoreboard();
        //scoreboards object
        Objective object = board.registerNewObjective("cloverboard", "clovermmo", "CloverBoard");
        object.setDisplaySlot(DisplaySlot.SIDEBAR);
        object.setDisplayName(ChatColor.DARK_AQUA + "CloverBoard");
        Player player = (Player) sender;
        Score sixteen = object.getScore(ChatColor.AQUA + " " + ChatColor.STRIKETHROUGH + "+--------------------+");
        sixteen.setScore(17);
        Score fifteen = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Player:");
        fifteen.setScore(16);
        Score fourteen = object.getScore(player.getDisplayName());
        fourteen.setScore(15);
        Score eleven = object.getScore(" ");
        eleven.setScore(14);
        Score ten = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Race:");
        ten.setScore(13);
        Score nine = object.getScore(ChatColor.WHITE + "Elf");
        nine.setScore(12);
        Score wwer = object.getScore(" ");
        wwer.setScore(11);
        Score we = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Class:");
        we.setScore(10);
        Score wee = object.getScore(ChatColor.WHITE + "Paladin");
        wee.setScore(9);
        Score eight = object.getScore("  ");
        eight.setScore(8);
        Score seven = object.getScore(ChatColor.GOLD + "" + ChatColor.BOLD + "» Level:");
        seven.setScore(7);
        Score six = object.getScore(String.valueOf(player.getLevel()));
        six.setScore(6);
        Score five = object.getScore(ChatColor.AQUA + "" + ChatColor.STRIKETHROUGH + "+--------------------+");
        five.setScore(5);
        player.setScoreboard(board);
        sender.sendMessage(DefaultConfig.getInstance().getPluginPrefix() + "Cloverboard loaded!");
    }

    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

}
