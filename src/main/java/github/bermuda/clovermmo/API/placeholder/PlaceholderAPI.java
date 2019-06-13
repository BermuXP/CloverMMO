package github.bermuda.clovermmo.API.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class PlaceholderAPI extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

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

        if (player == null) {
            return "No player";
        }

        if (s.contains("online")) {
            s=s.replace("online", String.valueOf(Bukkit.getOnlinePlayers().size()));
        }

        if (s.contains("displayname")) {
            s = s.replace("displayname", String.valueOf(player.getDisplayName()));

        }

        if (s.contains("playername")) {
            s=s.replace("playername", player.getName());
        }

        return s;
    }
}
