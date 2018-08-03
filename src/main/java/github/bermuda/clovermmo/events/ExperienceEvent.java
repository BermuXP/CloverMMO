package github.bermuda.clovermmo.events;

import github.bermuda.clovermmo.config.setconfig.AdvancedConfig;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class ExperienceEvent {

    public static void OnKillMobMessage(Player p, Entity e) {
        if (e.getName() != null) {
            String a;
            if (AdvancedConfig.advanced().getMobsCostumName(e.getName()).equals(e.getCustomName())) {
                a = e.getCustomName();
            } else if (AdvancedConfig.advanced().getMobsCostumName(e.getName()).equals(e.getName())) {
                a = e.getName();
            } else {
                a = AdvancedConfig.advanced().getMobsCostumName(e.getName());
            }
            int dnr = AdvancedConfig.advanced().getMobDifficulty(e.getName());
            int exp = AdvancedConfig.advanced().getDifficultyExp(dnr);

            p.sendMessage(clover.cloverprefix + "You have earned " + ChatColor.GREEN + exp + " exp " + ChatColor.WHITE + "for killing " + ChatColor.RED + a);

            int newexp = cc.getExp() + exp;
            cc.setExp(newexp);
            db.setExp(p, newexp);
        } else {
            p.sendMessage(clover.cloverprefix + "Error 404 Mob not found.");
        }
    }

}
