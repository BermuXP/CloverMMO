package github.bermuda.clovermmo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SyncCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        //todo Make 2 commands in here one after /sync db and one after /sync config
        // sync config needs to sync the config to the db
        // sync db needs to sync the db to the config






        //    public void InsertUserCharacteristics(Player player) {
//        Player playername = player.getPlayer();
//        int s = config.getInt("characteristics.strength");
//        int c = config.getInt("characteristics.constitution");
//        int w = config.getInt("characteristics.wisdom");
//        int ch = config.getInt("characteristics.charisma");
//        int i = config.getInt("characteristics.intelligence");
//        int d = config.getInt("characteristics.dexterity");
//        int l = config.getInt("characteristics.luck");
//        db.setUserCharacteristics(playername, s, c, w, ch, i, d, l);
//    }
        return false;
    }
}
