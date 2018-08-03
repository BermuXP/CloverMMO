package github.bermuda.clovermmo.commands.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class InventoryListener implements Listener {

    public void ProfileInventory(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("Profile")) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
                        Player p = (Player) e.getWhoClicked();
                        p.sendMessage(clover.cloverprefix + "Closed profileGUI");
                        e.setCancelled(true);
//                    }
                }
            }
//        }
    }
}