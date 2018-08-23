package github.bermuda.clovermmo.events;

import github.bermuda.clovermmo.database.data.UserData;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

import static github.bermuda.clovermmo.CloverMMO.db;

public class OnQuitEvent {
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        db.getUserData(p, new UserData());
    }
}