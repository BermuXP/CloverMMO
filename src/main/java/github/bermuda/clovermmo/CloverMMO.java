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
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {
    private DefaultConfig dc;
    public static CloverMMO clover;
    public static UseraccountDB cc;
    public PlaceholderAPI phapi;
    public PluginDescriptionFile pdFile = getDescription();
    public boolean noErrorsInConfigFiles = true;
    public FileConfiguration config = getConfig();
    public static Database db;
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
        db = new SQLite(clover);
        db.load();
        cc = new UseraccountDB();
        dc = new DefaultConfig();
        loadConfigFiles();
        // logs startup in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been enabled (v." + pdFile.getVersion() + ")");
        getServer().getPluginManager().registerEvents(clover, clover);
//        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
//            new PlaceholderAPI().register();
//            phapi = new PlaceholderAPI();
//            logger.info("PlaceHolderAPI found, using PlaceholderAPI. ");
//        } else {
//            logger.info("PlaceHolderAPI not found, using default Placeholders");
//        }
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

    public static String color(String Colors) {
        if (Colors == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', Colors);
    }

    private void loadConfigFiles() {
        scoreboardConfig.getInstance();
        raceConfig.getInstance();
        classConfig.getInstance();
    }

    public void debug(String message) {
        getLogger().info(message);
    }

    public void InsertUserCharacteristics(Player player) {
        Player playername = player.getPlayer();
        db.setUserCharacteristics(playername, 1, 1, 1, 1, 1, 1, 1);
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs disable in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v." + pdFile.getVersion() + ")");

    }
}
