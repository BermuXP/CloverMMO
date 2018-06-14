package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.level;
import github.bermuda.clovermmo.commands.rank;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {

    public static CloverMMO clover;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        PluginDescriptionFile pdFile = getDescription();
        clover = this;
        config.addDefault("text", true);
        config.options().copyDefaults(true);
        saveConfig();
        //logs in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been enabled (v."  + pdFile.getVersion() + ")");
        getServer().getPluginManager().registerEvents(this, this);
        //gets the commands from /command
        getCommand("level").setExecutor(new level());
        getCommand("rank").setExecutor(new rank());
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v."  + pdFile.getVersion() + ")");

    }
}
