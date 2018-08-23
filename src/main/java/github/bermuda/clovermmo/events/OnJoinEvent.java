package github.bermuda.clovermmo.events;

import github.bermuda.clovermmo.commands.gui.RaceGuiCMD;
import github.bermuda.clovermmo.config.setconfig.DefaultConfig;
import github.bermuda.clovermmo.database.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class OnJoinEvent {

    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        final Player p = event.getPlayer();
        PlayerData.onPlayerJoin(p);
        db.onRacePickDB(p);

        if (event.getPlayer().hasPlayedBefore()) {
            if (DefaultConfig.config().getOnReturnJoin() == true) {
                p.sendMessage("Welcome back " + p.getName());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(clover, new Runnable() {
                    public void run() {
                        RaceGuiCMD.RaceGUI(p);
                    }
                }, 5);
            }
        } else if (DefaultConfig.config().getOnFirstJoin() == true) {
            p.sendMessage("Welcome " + p.getDisplayName() + ", it's your first time here... to start you need to pick a race! what race are you?");
        }
    }
}