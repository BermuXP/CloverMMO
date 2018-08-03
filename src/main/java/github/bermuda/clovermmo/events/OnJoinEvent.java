package github.bermuda.clovermmo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class OnJoinEvent implements Listener {

    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        db.getUserData(p);
        if (event.getPlayer().hasPlayedBefore()) {
            if (clover.config.get("Onjoin.OnFirstJoinMessageEnable").equals(true)) {
                p.sendMessage("Welcome back " + p.getName().toLowerCase());
            }
        } else {
            if (clover.config.get("Onjoin.OnReturningJoinMessageEnable").equals(true)) {
                p.sendMessage("Welcome " + p + ", it's your first time here... to start you need to pick a race! what race are you?");
            }
        }
    }

}
