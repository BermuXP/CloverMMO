package bermuda.listerners;

import bermuda.Calculations;
import bermuda.MainCloverMMO;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;

public class EntityListener implements Listener {
    private static BukkitScheduler scheduler = Bukkit.getScheduler();
    private static HashMap<EntityType, String> mobNames = new HashMap<>();

    @EventHandler
    private void EntityDeathCaller(EntityDeathEvent event) {
        Entity e = event.getEntity();
        if (e.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent damagedBy = (EntityDamageByEntityEvent) e.getLastDamageCause();
            if (damagedBy.getDamager() instanceof Player) {
                Player p = (Player) damagedBy.getDamager();
                if (p.getName() != null) {
                    p.sendMessage("You have earned " + ChatColor.GREEN + "1 exp " + ChatColor.WHITE + "for killing " + ChatColor.RED + mobNames.get(damagedBy.getEntity().getType()));
                } else {
                    p.sendMessage("Mob not found.");
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityDamageEvent(EntityDamageEvent event) {
        Entity e = event.getEntity();
        if (!(e instanceof LivingEntity)) {
            return;
        }
        LivingEntity le = (LivingEntity) e;
        if (le.getNoDamageTicks() > le.getMaximumNoDamageTicks() / 2F) return;

        if (e instanceof Player) {
            //todo fix hp of player
        } else {
            showMobHealthBar(le);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntityRegain(EntityRegainHealthEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Player) {
            //todo fix hp of player
        } else if (e instanceof LivingEntity) {
            showMobHealthBar((LivingEntity) e);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onEntitySpawn(CreatureSpawnEvent event) {
        //show the bar on all the mobs
        final LivingEntity mob = event.getEntity();
        scheduler.scheduleSyncDelayedTask(MainCloverMMO.getPlugin(), () -> showMobHealthBar(mob), 1L);
    }

    /**
     * show health bar for mobs.
     *
     * @param mob Living entity
     */
    private static void showMobHealthBar(final LivingEntity mob) {
        if (mobNames.get(mob.getType()) == null) {
            mob.setCustomName(null);
            mobNames.put(mob.getType(), mob.getCustomName());
        }
        scheduler.scheduleSyncDelayedTask(MainCloverMMO.getPlugin(), () -> {
            double currentHp = Calculations.round(mob.getHealth(), 0);
            if (currentHp <= 0.0) {
                return;
            }
            mob.setCustomName(mobNames.get(mob.getType()) + getHealthArray(currentHp, mob.getMaxHealth(), 20));
        });
    }

    /**
     * Get health from health array.
     * @param amount
     * @param maxAmount
     * @param max
     * @return
     */
    private static String getHealthArray(double amount, double maxAmount, int max) {
        double amountToRound = max - ((amount/maxAmount) * max);
        int finalAmount = (int) Calculations.round(amountToRound, 0);
        String[] hpBar = healthBar();
        return hpBar[finalAmount];
    }

    private static String[] healthBar() {
        String[] healthArray = new String[21];
        healthArray[20] = "§c▌                   ";
        healthArray[19] = "§c▌                   ";
        healthArray[18] = "§c█                  ";
        healthArray[17] = "§c█▌                 ";
        healthArray[16] = "§c██                ";
        healthArray[15] = "§e██▌               ";
        healthArray[14] = "§e███              ";
        healthArray[13] = "§e███▌             ";
        healthArray[12] = "§e████            ";
        healthArray[11] = "§e████▌           ";
        healthArray[10] = "§e█████          ";
        healthArray[9] = "§a█████▌         ";
        healthArray[8] = "§a██████        ";
        healthArray[7] = "§a██████▌       ";
        healthArray[6] = "§a███████      ";
        healthArray[5] = "§a███████▌     ";
        healthArray[4] = "§a████████    ";
        healthArray[3] = "§a████████▌   ";
        healthArray[2] = "§a█████████  ";
        healthArray[1] = "§a█████████▌ ";
        healthArray[0] = "§a██████████";
        return healthArray;
    }

}
