package github.bermuda.clovermmo.abilities;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public abstract class ClassAbilities {

    private CloverMMO plugin;

    public ClassAbilities(CloverMMO cmmo) {
        this.plugin = cmmo;
    }

    public void hp(Player player) {
        //number must be replaceable in config.
        player.setMaxHealth(20);
//        player.setHealth(player.getMaxHealth());
    }

    public void bowdmg(EntityDamageByEntityEvent player) {
        if (player.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) player.getDamager();
            if (arrow.getShooter() instanceof Player) {
                player.setDamage(player.getDamage() + 2);
                // +3 needs to be replacedable in config
            }
        }
    }

    public void sworddmg() {

    }

    public void axedmg() {

    }

    public void hpregen() {

    }

    public void walkspeed() {

    }

    public void waterbreathing() {

    }

    public void fireresistance() {

    }

    public void nightvision() {

    }

}
