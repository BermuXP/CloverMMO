package github.bermuda.clovermmo.commands.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class ProfileGuiCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(clover.cloverprefix + "Profile GUI loaded.");
            db.getUserData(p);

            Inventory inv = Bukkit.createInventory(null, 9, "Profile");

            List<String> lore = Arrays.asList("Level: " + cc.getLevel(), "Exp: " + cc.getExp(), "Faction: " + cc.getFaction(), "Class: " + cc.getPclass(), "Spec: " + cc.getSpec());
            items(inv, p.getDisplayName(), Material.SKULL_ITEM, 0, ChatColor.GOLD, lore);

            List<String> lore2 = Arrays.asList(String.valueOf(cc.getStrength()));
            items(inv, "Strength", Material.DIAMOND_AXE, 1, ChatColor.GOLD, lore2);

            List<String> lore3 = Arrays.asList(String.valueOf(cc.getDexterity()));
            items(inv, "Dexterity", Material.SHIELD, 2, ChatColor.GOLD, lore3);

            List<String> lore4 = Arrays.asList(String.valueOf(cc.getConstitution()));
            items(inv, "Constitution", Material.BOW, 3, ChatColor.GOLD, lore4);

            List<String> lore5 = Arrays.asList(String.valueOf(cc.getWisdom()));
            items(inv, "Wisdom", Material.BOOK_AND_QUILL, 4, ChatColor.GOLD, lore5);

            List<String> lore6 = Arrays.asList(String.valueOf(cc.getIntelligence()));
            items(inv, "Intelligence", Material.BOOKSHELF, 5, ChatColor.GOLD, lore6);

            List<String> lore7 = Arrays.asList(String.valueOf(cc.getCharisma()));
            items(inv, "Charisma", Material.ARROW, 6, ChatColor.GOLD, lore7);

            List<String> lore8 = Arrays.asList(String.valueOf(cc.getLuck()));
            items(inv, "Luck", Material.EMERALD, 7, ChatColor.GOLD, lore8);

            List<String> lore9 = Arrays.asList(String.valueOf(cc.getPoint()));
            items(inv, "Points", Material.GOLD_NUGGET, 8, ChatColor.GOLD, lore9);

            p.openInventory(inv);
            return true;
        }
        return false;
    }

    public void items(Inventory inv, String dn, Material mat, int number, ChatColor displaycolor, List<String> lore) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displaycolor + dn);
        meta.setLore(lore);

        item.setItemMeta(meta);
        inv.addItem(item);
        inv.setItem(number, item);
    }
}
