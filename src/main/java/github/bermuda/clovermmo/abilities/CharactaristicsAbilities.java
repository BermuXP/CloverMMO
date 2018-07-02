package github.bermuda.clovermmo.abilities;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.entity.Player;

public abstract class CharactaristicsAbilities {

    private CloverMMO plugin;

    public CharactaristicsAbilities(CloverMMO cmmo) {
        this.plugin = cmmo;
    }

    //todo Strength
    public void maxhp(Player player, int mhp) {
        //number must be replaceable in config.
        player.setMaxHealth(mhp);
//        player.setHealth(player.getMaxHealth());
    }

    public void knockbackresist() {
        
    }
}
