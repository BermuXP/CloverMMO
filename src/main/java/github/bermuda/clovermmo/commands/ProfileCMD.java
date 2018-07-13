package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;

import github.bermuda.clovermmo.abilities.ClassAbilities;
import github.bermuda.clovermmo.database.SQLite;
import github.bermuda.clovermmo.API.placeholder.Placeholder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.color;
import static github.bermuda.clovermmo.CloverMMO.db;

public class ProfileCMD implements CommandExecutor {
    private ClassAbilities ability;

    public ProfileCMD(CloverMMO cmmo) {
        ability = new ClassAbilities(cmmo) {
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        db = new SQLite(clover);
        db.load();
        db.getUserData(player);
        List<String> text = clover.getConfig().getStringList("profile.message");

        for (String t : text) {
            String d = String.valueOf(Placeholder.onPlaceholderRequest(player, t));
            player.sendMessage(color(d));
        }
        return true;
    }

//    public void checkclass(Player player) {
//        db = new SQLite(clover);
//        db.load();
////        List<String> clas = clover.getConfig().getStringList("classes");
//        List<String> clas = db.getDatabaseClasses();
//
//        for (String s : clas) {
//            String classes = db.getClasses(player);
//            if (s.equalsIgnoreCase(classes)) {
//                int classmhp = clover.getConfig().getInt("classes." + classes + ".maxhp");
//                int racemhp = clover.getConfig().getInt("classes." + classes + ".maxhp");
//                int specmhp = clover.getConfig().getInt("classes." + classes + ".maxhp");
//                int calcmhp = classmhp + racemhp + specmhp;
//                this.ability.hp(player, calcmhp);
//            }
//        }
//        if (clas.isEmpty()) {
//            player.sendMessage(clover.cloverprefix + "You need to select a class before you can do this /class [classname] or /class to see all the possible classe");
//        }
//    }
//
//    private boolean setupChat() {
//        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
//        chat = rsp.getProvider();
//        return chat != null;
//    }
//
//    public static Chat getChat() {
//        return chat;
//    }

}
