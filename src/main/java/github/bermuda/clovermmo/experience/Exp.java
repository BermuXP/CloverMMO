package github.bermuda.clovermmo.experience;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class Exp {



    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity e = event.getEntity();
        if (e.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent) e.getLastDamageCause();
            if (Event.getDamager() instanceof Player) {
                String a = e.getName();
                //todo replace int exp
                int exp = 1;
                Player p = (Player) Event.getDamager();
                p.sendMessage(clover.cloverprefix + "You have earned " + ChatColor.GREEN + exp + " exp "+ ChatColor.WHITE +"by killing: "+ ChatColor.RED + a);
            }
        }
    }
}
