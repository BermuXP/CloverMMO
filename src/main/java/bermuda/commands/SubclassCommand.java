package bermuda.commands;

import bermuda.database.models.SubclassModel;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

@CommandAlias("faction|cfaction")
public class SubclassCommand extends BaseCommand {
    private HashMap<String, SubclassModel> subclasses;

    public SubclassCommand(HashMap<String, SubclassModel> HashFactions) {
        subclasses = HashFactions;
    }

    @Default
    @Subcommand("list")
    public void allSubclasses(CommandSender sender) {
        //todo show only subclasses beloging to the selected class and default users class
        for (Map.Entry<String, SubclassModel> entry : subclasses.entrySet()) {
            SubclassModel key = entry.getValue();
            sender.sendMessage(String.valueOf(key.getName()));
        }
    }

    @Subcommand("info")
    @Description("Show info about a subclass")
    public void subclassInfo(CommandSender sender, String[] arg) {
        boolean match = false;
        for (Map.Entry<String, SubclassModel> entry : subclasses.entrySet()) {
            SubclassModel key = entry.getValue();
            if (arg != null && arg[0].equalsIgnoreCase(key.getName())) {
                sender.sendMessage(key.getName());
                sender.sendMessage(key.getDescription());
            }
        }
        if(!match && arg != null) {
            sender.sendMessage("That subclass doesn't exist!");
        }else if(arg == null) {
            sender.sendMessage("Not the right format use: /subclass info [race name]");
        }
    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
