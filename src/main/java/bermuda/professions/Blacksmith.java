package bermuda.professions;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

import static bermuda.MainCloverMMO.getPlugin;

public class Blacksmith implements Listener {

    public void customRecipe(ItemStack item, String displayName, String desc, ArrayList<String> numbers) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(desc);
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        NamespacedKey key = new NamespacedKey(getPlugin(), getPlugin().getDescription().getName());
        ShapedRecipe r = new ShapedRecipe(key, item);

        r.shape("abc", "def", "ghi");
        int i = 1;
        for (String number: numbers) {
            Material materialChecker = Material.matchMaterial(number);
            Character rightShape = getRightShape(i);
            if(materialChecker != null && rightShape != null) {
                r.setIngredient(rightShape, materialChecker);
                i++;
            } else {
                getPlugin().getLogger().warning("Item: " + number + " Does not exist");
            }
        }

        getPlugin().getServer().addRecipe(r);
    }

    private Character getRightShape(int i) {
        switch (i) {
            case 1:
                return 'a';
            case 2:
                return 'b';
            case 3:
                return 'c';
            case 4:
                return 'd';
            case 5:
                return 'e';
            case 6:
                return 'f';
            case 7:
                return 'g';
            case 8:
                return 'h';
            case 9:
                return 'i';
            default:
                return null;
        }
    }


}
