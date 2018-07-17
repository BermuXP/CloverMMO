package github.bermuda.clovermmo.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class OnJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        Player playername = event.getPlayer();
        if (event.getPlayer().hasPlayedBefore()) {
            if (clover.config.get("Onjoin.OnFirstJoinMessageEnable").equals(true)) {
                event.getPlayer().sendMessage("Welcome back " + playername.getName().toLowerCase());
            }
        } else {
            db.addpoints(clover.config.getInt("Onjoin.AddPointsOnJoin"), playername.getName().toLowerCase());
            if (clover.config.get("Onjoin.OnReturningJoinMessageEnable").equals(true)) {
                event.getPlayer().sendMessage("Welcome " + playername + ", it's your first time here... to start you need to pick a race! what race are you?");
            }
        }
    }

}
