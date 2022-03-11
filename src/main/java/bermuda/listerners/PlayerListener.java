package bermuda.listerners;

import bermuda.MainCloverMMO;
import bermuda.events.BlockTypeEvents;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        int y = event.getPlayer().getLocation().getBlockY();
        int x = event.getPlayer().getLocation().getBlockX();
        int z = event.getPlayer().getLocation().getBlockZ();
        BlockTypeEvents.blockBreak(block, event.getPlayer());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        MainCloverMMO.getDbHelper().profileExists(String.valueOf(p.getUniqueId()));
        p.sendMessage("Welcome back " + p.getDisplayName() + "");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
    }




}
