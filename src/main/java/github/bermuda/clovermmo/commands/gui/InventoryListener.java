package github.bermuda.clovermmo.commands.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener {
    public void ProfileInventory(InventoryClickEvent e) {
        e.setCancelled(true);
    }
}