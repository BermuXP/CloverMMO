package bermuda.commands.ui;

import bermuda.database.models.SubclassModel;
import bermuda.listerners.UiListener;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SkillTreeCommand {

    /**
     * load subclass inventory.
     *
     * @param scmArray          ArrayList with subclasses for the specific class.
     * @param player            Player object with player data
     */
    public static void skillTreeForClass (ArrayList<SubclassModel> scmArray, Player player) {
        UiListener subMenu = new UiListener("Skills", 9, event -> {
            event.getPlayer().sendMessage("You have chosen " + event.getName());
            event.setWillClose(true);
        });


    }


}
