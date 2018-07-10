package github.bermuda.clovermmo.abilities;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public abstract class CharactaristicsAbilities {

    private CloverMMO plugin;

    public CharactaristicsAbilities(CloverMMO cmmo) {
        this.plugin = cmmo;
    }

    public void maxhp(Player player, int mhp) {
        //number must be replaceable in DefaultConfig.
//        player.setMaxHealth(mhp);
//        player.setHealth(player.getMaxHealth());
    }

    public void damageEvent(EntityDamageByEntityEvent event) { // just the eventhandler im using to listen for the event
        Player player = (Player) event.getEntity(); // the one not being hit
        Player cause = (Player) event.getDamager(); // the one hitting
        ItemStack stonesword = cause.getInventory().getItemInMainHand(); // dont worry i check below if its a stone sword

        if(cause.getInventory().getItemInMainHand().getType().equals(Material.STONE_SWORD)) { // wooooooooo
            if(stonesword.containsEnchantment(Enchantment.DAMAGE_ALL)) { // here you can do different levels like if its level 1 - so much damage if level 2 - so much damage etc
                event.setDamage(event.getDamage() - 40);

            }
        }
    }

    public void increasedamage(EntityDamageByEntityEvent event) { // just the eventhandler im using to listen for the event
        Player player = (Player) event.getEntity(); // the one not being hit
        Player cause = (Player) event.getDamager(); // the one hitting
        ItemStack stonesword = cause.getInventory().getItemInMainHand(); // dont worry i check below if its a stone sword

        if(cause.getInventory().getItemInOffHand().getType().equals(Material.STONE_SWORD)) { // wooooooooo
            if(stonesword.containsEnchantment(Enchantment.DAMAGE_ALL)) {
                event.setDamage(event.getDamage() + 40);
            }
        }
    }

}
