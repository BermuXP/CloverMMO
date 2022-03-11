package bermuda.commands;

import bermuda.MainCloverMMO;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import org.apache.commons.lang.LocaleUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("clovermmo")
public class CloverCommand extends BaseCommand {

    @Subcommand("language list")
    public void allLanguages(CommandSender sender, String[] arg) {
        //todo add a list off all the languages that are supported
    }

    @Subcommand("language set")
    public void setLanguage(CommandSender sender, String[] arg) {
        if(arg != null) {
            Player player = (Player) sender;
            BukkitCommandManager manager = MainCloverMMO.getManager();
            manager.setPlayerLocale(player, LocaleUtils.toLocale(arg[0]));
            manager.onLocaleChange((issuer, oldLocale, newLocale) -> {
                if (oldLocale == null) {
                    sender.sendMessage("successfully changed language");
                }
            });

        } else {
            sender.sendMessage("Invalid language");
        }
    }

    @Subcommand("support")
    public void requestSupport(CommandSender sender) {
        sender.sendMessage("https://discord.gg/DaheZKn");
    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
