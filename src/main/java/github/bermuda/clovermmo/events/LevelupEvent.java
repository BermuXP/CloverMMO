package github.bermuda.clovermmo.events;

import github.bermuda.clovermmo.config.setconfig.ProfileConfig;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class LevelupEvent {

    public static void levelup(EntityDeathEvent event, int safety, Player p) {
        if (safety > 5) {
            p.sendMessage(clover.cloverprefix + "Woooow ho there made you leveled a bit to much in an instance there");
            return;
        } else {
            safety++;
        }
        if (cc.getExp() >= ProfileConfig.getInstance().getExp(cc.getLevel())) {
            int d = ProfileConfig.getInstance().getPoints(cc.getLevel());
            db.setPoints(p, d);
            int newLevel = cc.getLevel();
            db.setLevel(p, ++newLevel);
            db.getUserData(p);
            p.sendMessage(clover.cloverprefix + "Yaaay! you leveled up to: " + cc.getLevel());
            levelup(event, safety, p);
        } else {
            p.sendMessage("Current level: " + cc.getLevel() + " exp: " + cc.getExp());
        }
    }
}


