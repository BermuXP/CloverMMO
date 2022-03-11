package bermuda.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.HelpCommand;


@CommandAlias("party|par|cparty|cpar")
public class PartyCommand extends BaseCommand {


    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
