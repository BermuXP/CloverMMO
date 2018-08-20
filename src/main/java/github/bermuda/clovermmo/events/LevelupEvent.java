package github.bermuda.clovermmo.events;

import github.bermuda.clovermmo.config.setconfig.ProfileConfig;
import org.bukkit.entity.Player;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class LevelupEvent {
    public static void levelup(int safety, Player p) {
        if (ProfileConfig.profile().getExp(cc.getLevel()) != 0) {
            if (cc.getExp() >= ProfileConfig.profile().getExp(cc.getLevel())) {
                if (safety > 5) {
                    p.sendMessage(clover.cloverprefix + "Woooow ho there made you leveled a bit to much in an instance there");
                    return;
                } else {
                    safety++;
                }
                int newLevel = cc.getLevel();
                db.LevelUp(p, ++newLevel, ProfileConfig.profile().getPoints(cc.getLevel()));
                cc.setLevel(++newLevel);
                p.sendMessage(clover.cloverprefix + "Yaaay! you leveled up to: " + cc.getLevel());
                levelup(safety, p);
            }
        }
    }
}


