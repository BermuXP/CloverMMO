package github.bermuda.clovermmo.commands.gui;

import github.bermuda.clovermmo.API.placeholder.Placeholder;
import github.bermuda.clovermmo.config.setconfig.ClassConfig;
import github.bermuda.clovermmo.config.setconfig.ProfileConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.color;
import static github.bermuda.clovermmo.CloverMMO.db;
import static github.bermuda.clovermmo.config.setconfig.ProfileConfig.profile;

public class ProfileGuiCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(clover.cloverprefix + "Profile GUI loaded.");
            db.getUserData(p);

            Inventory inv = Bukkit.createInventory(null, 9, profile().getGUIName());

            Set<String> amount = ProfileConfig.profile().getGUIKeys();

            for (String m : amount) {
                Material mat = Material.getMaterial(ProfileConfig.profile().getGUIItem(m));
                String dn = String.valueOf(Placeholder.onPlaceholderRequest(p, color(ProfileConfig.profile().getGUIDisplayname(m))));

                List<String> lore = new ArrayList<String>();
                for (String s : ProfileConfig.profile().getGUILore(m)) {
                    lore.add(String.valueOf(Placeholder.onPlaceholderRequest(p, color(s))));
                }

                int number = ProfileConfig.profile().getGUISpot(m);
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
            return true;
        }
        return false;
    }
}
