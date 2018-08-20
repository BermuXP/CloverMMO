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
import static github.bermuda.clovermmo.CloverMMO.db;

public class RaceGuiCMD {

    public static void RaceGUI(Player p) {
        db.getUserData(p);
        Inventory inv = Bukkit.createInventory(null, 27, "Select a race menu");
        Set<String> amount = RaceConfig.getInstance().getRaceNames();
        int ininventorywindow = -1;
        for (String m : amount) {
            ininventorywindow++;

            Material mat = Material.getMaterial(RaceConfig.getInstance().getRaceItem(m));
            String dn = String.valueOf(Placeholder.onPlaceholderRequest(p, color(m)));
            List<String> lore = new ArrayList<String>();

            int str = RaceConfig.getInstance().getRaceStrength(m);
            int dex = RaceConfig.getInstance().getRaceDexterity(m);
            int con = RaceConfig.getInstance().getRaceConstitution(m);
            int intel = RaceConfig.getInstance().getRaceIntelligence(m);
            int wis = RaceConfig.getInstance().getRaceWisdom(m);
            int charm = RaceConfig.getInstance().getRaceCharisma(m);
            int luck = RaceConfig.getInstance().getRaceLuck(m);

            lore.add(color("&cStrength: &f" + String.valueOf(str)));
            lore.add(color("&aDexterity: &f" + String.valueOf(dex)));
            lore.add(color("&bConstitution: &f" + String.valueOf(con)));
            lore.add(color("&3Wisdom: &f" + String.valueOf(wis)));
            lore.add(color("&fIntelligence: &f" + String.valueOf(intel)));
            lore.add(color("&dCharisma: &f" + String.valueOf(charm)));
            lore.add(color("&eLuck: &f" + String.valueOf(luck)));
            lore.add(color("&6Lore:"));
            lore.addAll(RaceConfig.getInstance().getRaceLore(m));

            ItemStack item = new ItemStack(mat);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(dn);
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
            inv.addItem(item);
            inv.setItem(ininventorywindow, item);

        }
        p.openInventory(inv);
    }

}
