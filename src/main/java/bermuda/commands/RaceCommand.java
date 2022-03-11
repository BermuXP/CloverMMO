package bermuda.commands;

import bermuda.database.models.RacesModel;
import bermuda.database.models.StatsModel;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

@CommandAlias("race")
public class RaceCommand extends BaseCommand {
    private HashMap<String, RacesModel> races;
    private HashMap<Integer, StatsModel> stats;

    public RaceCommand(HashMap<String, RacesModel> HashRaces, HashMap<Integer, StatsModel> HashStats) {
        races = HashRaces;
        stats = HashStats;
    }

    @Default
    @Subcommand("list")
    public void allRaces(CommandSender sender) {
        for (Map.Entry<String, RacesModel> entry : races.entrySet()) {
            RacesModel key = entry.getValue();
            sender.sendMessage(key.getName());
        }
    }

    @Subcommand("select|sel")
    @Description("Select a race")
    public void selectRace(CommandSender sender, String[] arg) {
        for (Map.Entry<String, RacesModel> entry : races.entrySet()) {
            RacesModel key = entry.getValue();
            if(arg[0].equalsIgnoreCase(key.getName())) {
                sender.sendMessage(key.getName() + " selected as your race!");
            }
        }
    }

    @Subcommand("info|in")
    @Description("Show info about the filled in race")
    public void infoRace(CommandSender sender, String[] arg) {
        boolean match = false;
        for (Map.Entry<String, RacesModel> entry : races.entrySet()) {
            RacesModel key = entry.getValue();
            if (arg != null && arg[0].equalsIgnoreCase(key.getName())) {
                StatsModel raceStats = stats.get(key.getStats_id());
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
        if(!match && arg != null) {
            sender.sendMessage("That race doesn't exist!");
        }else if(arg == null) {
            sender.sendMessage( "Not the right format use: /race info [race name]");
        }
    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
