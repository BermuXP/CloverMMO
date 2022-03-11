package bermuda.commands;

import bermuda.database.managers.Api;
import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import org.bukkit.command.CommandSender;

@CommandAlias("capi")
public class ApiCommand extends BaseCommand {

    @Subcommand("connect")
    public void connectAPI(CommandSender sender) {
        Api api = new Api();
        api.sendAllSeverData();
    }

}
