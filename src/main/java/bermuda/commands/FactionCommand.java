package bermuda.commands;

import bermuda.database.models.FactionModel;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

@CommandAlias("cfaction|cf")
public class FactionCommand extends BaseCommand {
    private HashMap<String, FactionModel> factions;

    public FactionCommand(HashMap<String, FactionModel> HashFactions) {
        factions = HashFactions;
    }

    @Default
    @Subcommand("list")
    public void allClasses(CommandSender sender) {
        for (Map.Entry<String, FactionModel> entry : factions.entrySet()) {
            FactionModel key = entry.getValue();
            sender.sendMessage(String.valueOf(key.getName()));
        }
    }

    @Subcommand("info")
    @Description("Show info about a faction")
    public void factionInfo(CommandSender sender, String[] arg) {
        boolean match = false;
        for (Map.Entry<String, FactionModel> entry : factions.entrySet()) {
            FactionModel key = entry.getValue();
            if (arg != null && arg[0].equalsIgnoreCase(key.getName())) {
                sender.sendMessage(key.getName());
                sender.sendMessage(key.getDescription());
            }
        }
        if(!match && arg != null) {
            sender.sendMessage("That faction doesn't exist!");
        }else if(arg == null) {
            sender.sendMessage("Not the right format use: /faction info [race name]");
        }
    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
