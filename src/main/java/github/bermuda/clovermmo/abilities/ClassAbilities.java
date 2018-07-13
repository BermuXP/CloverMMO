package github.bermuda.clovermmo.abilities;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.inventory.ItemStack;

public abstract class ClassAbilities {

    private CloverMMO clovermmo;

    public ClassAbilities(CloverMMO cmmo) {
        this.clovermmo = cmmo;
    }

    public void hp(Player player, int mhp) {
        //number must be replaceable in DefaultConfig.
//        player.setMaxHealth(mhp);
//        player.setHealth(player.getMaxHealth());
    }

    //todo finish, not done
    public void basichpregen(EntityRegainHealthEvent event, int hpregen) {
        Player player = (Player) event.getEntity();
        player.setHealth(player.getHealth() + hpregen);
    }

    public void BowDamageEvent(EntityDamageByEntityEvent player, int bowdamage) {
        if (player.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) player.getDamager();
            if (arrow.getShooter() instanceof Player) {
                player.setDamage(player.getDamage() + bowdamage);
            }
        }
    }
//todo remove arrow dmg and rewrite to all sword damage, if that does not work write a few other functions for all djkind of sword dmg
    public void SwordDamageEvent(EntityDamageByEntityEvent player, int sworddamage) {
        if (player.getDamager() instanceof Arrow) {

            Player cause = (Player) player.getDamager(); // the one hitting
            ItemStack stonesword = cause.getInventory().getItemInMainHand();

            Arrow arrow = (Arrow) player.getDamager();
            if (arrow.getShooter() instanceof Player) {
                player.setDamage(player.getDamage() + sworddamage);
            }
        }
    }

    @EventHandler
    public void damageEvent(EntityDamageByEntityEvent event) { // just the eventhandler im using to listen for the event
        Player player = (Player) event.getEntity(); // the one not being hit
        Player cause = (Player) event.getDamager(); // the one hitting
        ItemStack stonesword = cause.getInventory().getItemInMainHand(); // dont worry i check below if its a stone sword

        if (cause.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)) { // wooooooooo
            if (stonesword.containsEnchantment(Enchantment.DAMAGE_ALL)) { // here you can do different levels like if its level 1 - so much damage if level 2 - so much damage etc
                event.setDamage(event.getDamage() - 40);

            }
        }
    }


}
