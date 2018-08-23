package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.commands.gui.InventoryListener;
import github.bermuda.clovermmo.commands.gui.ProfileGuiCMD;
import github.bermuda.clovermmo.config.setconfig.*;
import github.bermuda.clovermmo.database.data.PlayerData;
import github.bermuda.clovermmo.database.data.UserData;
import github.bermuda.clovermmo.database.Database;

import github.bermuda.clovermmo.database.SQLite;
import github.bermuda.clovermmo.events.DamageEvent;
import github.bermuda.clovermmo.events.ExperienceEvent;
import github.bermuda.clovermmo.events.OnJoinEvent;
import github.bermuda.clovermmo.events.OnQuitEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;
import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {
    public static CloverMMO clover;
    public static UserData cc;
    //    public PlaceholderAPI phapi;
    public PluginDescriptionFile pdFile = getDescription();
    public boolean noErrorsInConfigFiles = true;
    public FileConfiguration config = getConfig();
    public static Database db;
    public String cloverprefix = ChatColor.BOLD + "" + ChatColor.GREEN + "CloverMMO " + ChatColor.BLUE + "» " + ChatColor.WHITE;

    @Override
    public void onEnable() {
        clover = this;
        cc = new UserData(); //todo remove
        new ExperienceEvent();
        new PlayerData();

        loadConfigFiles();


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
        commands();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        new OnJoinEvent().onPlayerJoinEvent(event);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        new OnQuitEvent().onPlayerQuitEvent(event);
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        new InventoryListener().ProfileInventory(e);
    }

    @EventHandler
    private void EntityDeathCaller(EntityDeathEvent e) {
        new DamageEvent().onEntityDeath(e);
    }

    private void commands() {
//        getCommand("sync").setExecutor(new SyncCMD());
        getCommand("clovermmo").setExecutor(new ClovermmoCMD());
        getCommand("cmmo").setExecutor(new ClovermmoCMD());
        getCommand("cloverboard").setExecutor(new CloverboardCMD());
        getCommand("race").setExecutor(new RaceCMD());
        getCommand("class").setExecutor(new ClassCMD());

        if (DefaultConfig.config().getGuiOrChatProfile().equalsIgnoreCase("gui")) {
            getCommand("profile").setExecutor(new ProfileGuiCMD());
        } else {
            getCommand("profile").setExecutor(new ProfileCMD());
        }

        getCommand("faction").setExecutor(new FactionCMD());
        getCommand("cloverreload").setExecutor(new ReloadCMD());
    }

    public static String color(String Colors) {
        if (Colors == null) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', Colors);
    }

    private void loadConfigFiles() {
        ProfileConfig.profile();
        DefaultConfig.config();
        FactionConfig.getInstance();
        RaceConfig.getInstance();

        db = new SQLite(clover);
        db.load();

        Set<String> race = RaceConfig.getInstance().getRaceNames();
        for (String r : race) {
            db.setDatabaseRaces(r);
        }

        ClassConfig.getInstance();
        Set<String> classes = ClassConfig.getInstance().getClassNames();
        for (String c : classes) {
            db.setDatabaseClasses(c);
        }
        AdvancedConfig.advanced();
    }

    public void debug(String message) {
        getLogger().info(message);
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs disable in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v." + pdFile.getVersion() + ")");
    }
}
