package github.bermuda.clovermmo.commands.gui;

import github.bermuda.clovermmo.API.placeholder.Placeholder;
import github.bermuda.clovermmo.config.setconfig.RaceConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static github.bermuda.clovermmo.CloverMMO.color;

public class RaceGuiCMD {

    public static void RaceGUI(Player p) {
        String ms = "";
        Inventory inv = Bukkit.createInventory(null, RaceConfig.getInstance().getGuiRows(), String.valueOf(Placeholder.onPlaceholderRequest(ms, p, color(RaceConfig.getInstance().getGuiTitle()))));
        Set<String> amount = RaceConfig.getInstance().getGuiRaceNames();

        for (String m : amount) {
            int number = RaceConfig.getInstance().getGuiRaceSpot(m);

            Material mat = Material.getMaterial(RaceConfig.getInstance().getRaceItem(m));
            String dn = String.valueOf(Placeholder.onPlaceholderRequest(m, p, color(m)));

            List<String> lore = new ArrayList<String>();
            for (String fl : RaceConfig.getInstance().getGuiLore()) {
                lore.add(String.valueOf(Placeholder.onPlaceholderRequest(m, p, color(fl))));
            }
            for (String fn : RaceConfig.getInstance().getGuiRaceLore(m)) {
                lore.add(String.valueOf(Placeholder.onPlaceholderRequest(m, p, color(fn))));
            }
            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(dn);
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            inv.addItem(item);
            inv.setItem(number, item);
        }
        p.openInventory(inv);
    }
}
