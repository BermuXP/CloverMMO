package github.bermuda.clovermmo.API;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "";
    }

    @Override
    public String getPlugin() {
        return null;
    }

    @Override
    public String getAuthor() {
        return "Bermuda";
    }

    @Override
    public String getVersion() {
        return clover.pdFile.getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String s) {

        if(player == null){
            return "";
        }

        if(s.equalsIgnoreCase("onlines")){
            return String.valueOf(Bukkit.getOnlinePlayers().size());
        }


        if(s.equalsIgnoreCase("displayname")){
            return String.valueOf(player.getDisplayName());
        }

        if(s.equalsIgnoreCase("playername")){
            return player.getName();
        }

        return null;
    }
}
