package github.bermuda.clovermmo.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class DamageEvent {
    public void onEntityDeath(EntityDeathEvent event) {
        Entity e = event.getEntity();
        if (e.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent Event = (EntityDamageByEntityEvent) e.getLastDamageCause();
            if (Event.getDamager() instanceof Player) {
                Player p = (Player) Event.getDamager();
                //put stuff here that needs to happen on a mob dying by anything but arrows
                ExperienceEvent.OnKillMobMessage(p, e);
                LevelupEvent.levelup(0, p);
            } else if (Event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) Event.getDamager();
                Player p = (Player) arrow.getShooter();
                //put stuff here that needs to happen on a mob dying by an ARROW
                ExperienceEvent.OnKillMobMessage(p, e);
                LevelupEvent.levelup(0, p);
            }
        }
    }
}
