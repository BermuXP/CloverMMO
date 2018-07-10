package github.bermuda.clovermmo;

import github.bermuda.clovermmo.API.PlaceholderAPI;
import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.config.DefaultConfig;
import github.bermuda.clovermmo.config.classConfig;
import github.bermuda.clovermmo.config.raceConfig;
import github.bermuda.clovermmo.config.scoreboardConfig;
import github.bermuda.clovermmo.database.UseraccountDB;
import github.bermuda.clovermmo.database.Database;

import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {
    private DefaultConfig dc;
    public static CloverMMO clover;
    public static UseraccountDB cc;
    public static PlaceholderAPI phapi;
    public PluginDescriptionFile pdFile = getDescription();
    public boolean noErrorsInConfigFiles = true;
    FileConfiguration config = getConfig();
    private Database db;
    public String cloverprefix = ChatColor.BOLD + "" + ChatColor.GREEN + "CloverMMO " + ChatColor.BLUE + "» " + ChatColor.WHITE;

    public class ConfigListener implements Listener {
        CloverMMO clover;

        public ConfigListener(CloverMMO instance) {
            clover = instance;
        }
    }

    @Override
    public void onEnable() {
        clover = this;
        clover.saveDefaultConfig();
        clover.db = new SQLite(clover);
        clover.db.load();
        cc = new UseraccountDB();
        dc = new DefaultConfig();
//        PluginDescriptionFile pdFile = getDescription();
        loadConfigFiles();
        // logs startup in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been enabled (v." + pdFile.getVersion() + ")");
        getServer().getPluginManager().registerEvents(clover, clover);
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderAPI().register();
        }
        // gets the commands from the commands folder.
        commands();
    }

    private void commands() {
//        String cmmo = "cmmo";
        getCommand("clovermmo").setExecutor(new ClovermmoCMD());
        getCommand("level").setExecutor(new LevelCMD());
        getCommand("cloverboard").setExecutor(new CloverboardCMD());
        getCommand("race").setExecutor(new RaceCMD(clover));
        getCommand("class").setExecutor(new ClassCMD(clover));
        getCommand("profile").setExecutor(new ProfileCMD(clover));
        getCommand("faction").setExecutor(new FactionCMD(clover));
    }

    private void loadConfigFiles() {
        scoreboardConfig.getInstance();
        raceConfig.getInstance();
        classConfig.getInstance();
    }

    public void debug(String message) {
        getLogger().info("CloverMMO debug>> " + message);
    }

    public void InsertUserCharacteristics(Player player) {
        Player playername = player.getPlayer();
        db = new SQLite(clover);
        db.load();
        db.setUserCharacteristics(playername, 1, 1, 1, 1, 1, 1, 1);
    }

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

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs disable in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v." + pdFile.getVersion() + ")");

    }
}
