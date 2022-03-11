package bermuda.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.HelpCommand;
import org.bukkit.command.CommandSender;


@CommandAlias("dungeon|dung")
public class DungeonCommand  extends BaseCommand {

    public void createDungeonBorder() {

    }

    public void deleteDungeon() {

    }

    public void editDungeonBorder() {

    }

    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }
}
