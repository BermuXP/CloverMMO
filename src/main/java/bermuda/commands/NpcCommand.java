package bermuda.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

@CommandAlias("cnpc")
public class NpcCommand extends BaseCommand {

    @Default
    @Subcommand("create")
    public void createNPC(CommandSender sender) {

    }

    @Default
    @Subcommand("remove")
    public void removeNPC(CommandSender sender) {

    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}

