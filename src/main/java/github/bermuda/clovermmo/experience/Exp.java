package github.bermuda.clovermmo.experience;

import github.bermuda.clovermmo.config.setconfig.AdvancedConfig;
import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class Exp {
    public String a;

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity e = event.getEntity();
        if (e.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent) e.getLastDamageCause();
            if (Event.getDamager() instanceof Player) {
                Player p = (Player) Event.getDamager();
                Message(p, e);
            } else if (Event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) Event.getDamager();
                Player p = (Player) arrow.getShooter();
                Message(p, e);
            }
        }
    }

    public void Message(Player p, Entity e) {
        if (e.getName() != null) {
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
            DBexp(p, exp);
        } else {
            p.sendMessage(clover.cloverprefix + "ehm whut? either your mob has like no name.. or the mob you killed doesn't exist, either way, you shouldn't be seeing this so.. you know contact the plugin developer");
        }
    }

    public void DBexp(Player p, int exp) {
        db = new SQLite(clover);
        db.load();
        db.getUserData(p);
        int newexp = cc.getExp() + exp;
        db.setExp(p, newexp);
    }
}
