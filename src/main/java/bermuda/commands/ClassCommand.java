package bermuda.commands;

import bermuda.CustomInventory;
import bermuda.database.models.ClassModel;
import bermuda.database.models.StatsModel;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@CommandAlias("class")
public class ClassCommand extends BaseCommand {
    private HashMap<String, ClassModel> classes;
    private HashMap<Integer, StatsModel> stats;

    public ClassCommand(HashMap<String, ClassModel> HashRaces, HashMap<Integer, StatsModel> HashStats) {
        classes = HashRaces;
        stats = HashStats;
    }

    @Default
    @Subcommand("list")
    public void allClasses(CommandSender sender) {
        Player player = (Player) sender;
        CustomInventory.classesInventory(classes, player);
    }

    @Subcommand("select|sel")
    @Description("Select a race")
    public void selectClass(CommandSender sender, String[] arg) {
        for (Map.Entry<String, ClassModel> entry : classes.entrySet()) {
            ClassModel key = entry.getValue();
            if (arg[0].equalsIgnoreCase(key.getName())) {
                sender.sendMessage(key.getName() + " was selected as your subclass!");
            }
        }
    }

    @Subcommand("info|in")
    @Description("Show info about the filled in race")
    public void classInfo(CommandSender sender, String[] arg) {
        boolean match = false;
        for (Map.Entry<String, ClassModel> entry : classes.entrySet()) {
            ClassModel key = entry.getValue();
            if (arg != null && arg[0].equalsIgnoreCase(key.getName())) {
                StatsModel raceStats = stats.get(key.getStatsId());
                sender.sendMessage(key.getName());
                sender.sendMessage("Race: " + key.getName());
                sender.sendMessage("Strength: " + raceStats.getStrength());
                sender.sendMessage("Dexterity: " + raceStats.getDexterity());
                sender.sendMessage("Constitution: " + raceStats.getConstitution());
                sender.sendMessage("Wisdom: " + raceStats.getWisdom());
                sender.sendMessage("Charisma: " + raceStats.getCharisma());
                sender.sendMessage("Intelligence: " + raceStats.getIntelligence());
                sender.sendMessage("Luck: " + raceStats.getLuck());
                match = true;
            }
        }
        if (!match && arg != null) {
            sender.sendMessage("That class doesn't exist!");
        } else if (arg == null) {
            sender.sendMessage("Not the right format use: /class info [race name]");
        }
    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
