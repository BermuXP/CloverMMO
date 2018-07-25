package github.bermuda.clovermmo.commands.gui;

import github.bermuda.clovermmo.commands.ProfileCMD;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static org.bukkit.Material.DIAMOND_SWORD;

public class ProfileGuiCMD implements Listener, CommandExecutor {

    // Create a new inventory, with no owner, a size of nine, called example
    public static Inventory inv = Bukkit.createInventory(null, 9, "Profile");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player player = (Player) sender;
        player.openInventory(inv);
        initializeItems(player);
        return false;
    }

    // You can call this whenever you want to put the items in
    public void initializeItems(Player player) {
//        inv.addItem(createGuiItem(player.getDisplayName(), new ArrayList<String>(Arrays.asList(cc.getPclass(), String.valueOf(cc.getLevel()), String.valueOf(cc.getExp()))), Material.SKULL_ITEM));
        inv.addItem(createGuiItem("Strength", new ArrayList<String>(Arrays.asList(player.getDisplayName())), DIAMOND_SWORD));
//        inv.addItem(createGuiItem("Dexterity", new ArrayList<String>(Arrays.asList(String.valueOf(cc.getDexterity()))), Material.SHIELD));
//        inv.addItem(createGuiItem("Constitution", new ArrayList<String>(Arrays.asList(String.valueOf(cc.getConstitution()))), Material.TOTEM));
//        inv.addItem(createGuiItem("Wisdom", new ArrayList<String>(Arrays.asList(String.valueOf(cc.getWisdom()))), Material.BOOK));
//        inv.addItem(createGuiItem("Intelligence", new ArrayList<String>(Arrays.asList(String.valueOf(cc.getIntelligence()))), Material.EMPTY_MAP));
//        inv.addItem(createGuiItem("Charisma", new ArrayList<String>(Arrays.asList(String.valueOf(cc.getCharisma()))), Material.ARROW));
//        inv.addItem(createGuiItem("Luck", new ArrayList<String>(Arrays.asList(String.valueOf(cc.getLuck()))), Material.DIAMOND));
    }

    public ItemStack createGuiItem(String name, ArrayList<String> desc, Material mat) {
        ItemStack i = new ItemStack(mat, 1);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.setDisplayName(name);
        iMeta.setLore(desc);
        i.setItemMeta(iMeta);
        return i;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        String invName = e.getInventory().getName();
        if (!invName.equals(inv.getName())) {
            return;
        }

        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();


        if (clickedItem == null) {
            return;
        }

        if (!clickedItem.hasItemMeta()) {
            return;
        }

        ItemMeta meta = clickedItem.getItemMeta();

        if (!meta.hasDisplayName()) {
            return;
        }

        if (meta.getDisplayName().equals("Strength")) {
            p.sendMessage("you clicked strength");
            return;
        }
    }
}
