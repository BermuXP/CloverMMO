package bermuda.commands;

import bermuda.MainCloverMMO;
import bermuda.database.models.ProfileModel;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.entity.Player;

@CommandAlias("profile|cprofile")
public class ProfileCommand extends BaseCommand {

    @Default
    @Subcommand("show")
    public void showProfile(Player sender) {
        sender.getUniqueId();
        ProfileModel pm = MainCloverMMO.getDbHelper().getSpecficProfile(String.valueOf(sender.getUniqueId()));
        if(pm != null) {
            pm = MainCloverMMO.getDbHelper().addNewProfile(sender, sender.getDisplayName());
        }
        if(pm.getName() != null) {
            sender.sendMessage("Name: " + pm.getName());
        }
    }

    @Subcommand("select|sel")
    public void selectProfile() {

    }

    @HelpCommand
    public static void onHelp(CommandHelp help) {
        help.showHelp();
    }
}
