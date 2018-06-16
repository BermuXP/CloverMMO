package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.Level;
import github.bermuda.clovermmo.commands.Profile;
import github.bermuda.clovermmo.commands.Rank;

import org.bukkit.configuration.file.FileConfiguration;
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
<<<<<<< HEAD
        //gets the commands from /command
        getCommand("Level").setExecutor(new Level());
        getCommand("Profile").setExecutor(new Profile());
        getCommand("Rank").setExecutor(new Rank());
=======
        //gets the commands from the commands folder.
        getCommand("level").setExecutor(new level());
        getCommand("rank").setExecutor(new rank());
>>>>>>> ee3b6ae04f8db72b997a9aa99316f848797b77ed
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v."  + pdFile.getVersion() + ")");

    }
}
