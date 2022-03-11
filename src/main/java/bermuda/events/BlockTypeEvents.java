package bermuda.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockTypeEvents {

    public static void blockBreak(Block b, Player p) {
        switch (b.getType()) {
            case STONE: {
                break;
            }
        }
    }

}
