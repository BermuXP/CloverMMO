package bermuda.commands.professions;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

@CommandAlias("blacksmith")
public class BlacksmithCMD extends BaseCommand {

    @Subcommand("info")
    public void info(CommandSender sender, String[] arg) {

    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
