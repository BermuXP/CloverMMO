package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.config.scoreboarConfig;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {

    public static CloverMMO clover;
    public boolean noErrorsInConfigFiles = true;
    FileConfiguration config = getConfig();
    private Database db;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();

        clover = this;
        this.db = new SQLite(clover);
        this.db.load();
        clover.getConfig().set("player-name", "kiro is awesome");
        clover.saveConfig();
        PluginDescriptionFile pdFile = getDescription();
//        config.addDefault("race:", "Human");
//        config.options().copyDefaults(true);
        loadConfigFiles();
        String stringValue = "Hello World!";
        getConfig().set("path.to.string", stringValue);
        //logs startup in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been enabled (v."  + pdFile.getVersion() + ")");
        getServer().getPluginManager().registerEvents(this, this);
        //gets the commands from the commands folder.
        commands();
    }

    private void commands() {
        getCommand("level").setExecutor(new level());
        getCommand("rank").setExecutor(new rank());
        getCommand("cloverboard").setExecutor(new cloverboard());
        getCommand("clovermmo").setExecutor(new clovermmo());
        getCommand("race").setExecutor(new race());
    }

    public Database getRDatabase() {
        return this.db;
    }

    private void loadConfigFiles() {
        scoreboarConfig.getInstance();
    }

    public void debug(String message) {
        getLogger().info("[Debug] " + message);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if(!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().sendMessage("Welcome" + event.getPlayer() + ", it's your first time here... to start you need to pick a race! what race are you?");
        } else {
            event.getPlayer().sendMessage("Welcome back" + event.getPlayer());
        }
    }


    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs disable in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v."  + pdFile.getVersion() + ")");

    }
}
