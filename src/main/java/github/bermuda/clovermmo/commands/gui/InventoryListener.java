package github.bermuda.clovermmo.commands.gui;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    public void ProfileInventory(InventoryClickEvent e) {
        if (e.getInventory().getTitle().equals("Profile")) {
//            if (e.getCurrentItem().getItemMeta() != null) {
                if (e.getCurrentItem().getItemMeta().getDisplayName() != null) {
//                    if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.RED + "MY APPLE")) {
                        //code
//                        Player p = (Player) e.getWhoClicked();
//                        p.sendMessage("");
                        e.setCancelled(true);
//                    }
                }
            }
//        }
    }
}