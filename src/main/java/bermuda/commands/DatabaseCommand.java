package bermuda.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.HelpCommand;

public class DatabaseCommand extends BaseCommand {

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
