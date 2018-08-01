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
                ExperienceEvent.Message(p, e);
                LevelupEvent.levelup(event, 0, p);
            } else if (Event.getDamager() instanceof Arrow) {
                Arrow arrow = (Arrow) Event.getDamager();
                Player p = (Player) arrow.getShooter();
                ExperienceEvent.Message(p, e);
                LevelupEvent.levelup(event, 0, p);
            }
        }
    }
}
