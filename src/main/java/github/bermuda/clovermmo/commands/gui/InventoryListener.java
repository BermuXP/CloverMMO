package github.bermuda.clovermmo.commands.gui;

import github.bermuda.clovermmo.config.setconfig.RaceConfig;
import github.bermuda.clovermmo.database.model.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Collections;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;

public class InventoryListener {
    public void ProfileInventory(InventoryClickEvent e) {
        for (String r : RaceConfig.getInstance().getGuiRaceNames()) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(r)) {
                final Player p = (Player) e.getWhoClicked();
//                cc.setRace(r);

//                PlayerData.getVariable(p, "Races", "RacesModel", Collections.singletonList(r));
                p.sendMessage(clover.cloverprefix + r + " Race selected");
                e.setCancelled(true);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(clover, new Runnable() {
                    public void run() {
                        p.closeInventory();
                    }
                }, 5);
            } else {
                e.setCancelled(true);
            }
        }
        e.setCancelled(true);
    }
}
