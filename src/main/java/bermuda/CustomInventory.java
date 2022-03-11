package bermuda;

import bermuda.database.models.ClassModel;
import bermuda.database.models.SubclassModel;
import bermuda.listerners.UiListener;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static bermuda.MainCloverMMO.getDbHelper;

public class CustomInventory {

    /**
     * load subclass inventory.
     *
     * @param scmArray          ArrayList with subclasses for the specific class.
     * @param player            Player object with player data
     */
    public static void subClassInventory(ArrayList<SubclassModel> scmArray, Player player) {
        UiListener subMenu = new UiListener("Subclasses", 9, event -> {
            event.getPlayer().sendMessage("You have chosen " + event.getName());
            event.setWillClose(true);
        });

        int i = 0;
        for (SubclassModel scm : scmArray) {
            subMenu.setOption(i, new ItemStack(Material.IRON_AXE, 1), scm.getName(), scm.getDescription());
            i++;
        }
        subMenu.open(player);
    }

    /**
     * Create inventory for classes.
     * @param classes       HashMap with all the classes.
     * @param player        Player object with player data.
     */
    public static void classesInventory(HashMap<String, ClassModel> classes, Player player) {
        UiListener menu = new UiListener("Classes", 9, event -> {
            ClassModel cm = classes.get(event.getName());
            ArrayList<SubclassModel> scmArray = getDbHelper().getSpecificSubClasses(cm.getId());
            event.getPlayer().sendMessage("You have chosen " + event.getName());
            event.setWillClose(true);
            CustomInventory.subClassInventory(scmArray, player);
        });

        int i = 0;
        for (Map.Entry<String, ClassModel> entry : classes.entrySet()) {
            ClassModel value = entry.getValue();
            menu.setOption(i, new ItemStack(Material.SHIELD, 1), value.getName(), value.getDescription());
            i++;
        }
        menu.open(player);
    }
}
