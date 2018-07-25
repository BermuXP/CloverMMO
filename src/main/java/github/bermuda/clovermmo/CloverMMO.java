package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.commands.gui.ProfileGuiCMD;
import github.bermuda.clovermmo.config.setconfig.*;
import github.bermuda.clovermmo.database.data.UserData;
import github.bermuda.clovermmo.database.Database;

import github.bermuda.clovermmo.database.SQLite;
import github.bermuda.clovermmo.experience.Exp;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
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
        clover.saveDefaultConfig();
        db = new SQLite(clover);
        db.load();

        cc = new UserData();
        new DefaultConfig();
        new Exp();
//
//        onjoin.onPlayerJoinEvent();

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
    private void EntityDeathCaller(EntityDeathEvent e) {
        new Exp().onEntityDeath(e);
    }

    private void commands() {
//        String cmmo = "cmmo";
        getCommand("clovermmo").setExecutor(new ClovermmoCMD());
        getCommand("cloverboard").setExecutor(new CloverboardCMD());
        getCommand("race").setExecutor(new RaceCMD());
        getCommand("class").setExecutor(new ClassCMD());
        if (config.get("profile.GuiOrChat").equals("gui")) {
            getCommand("profile").setExecutor(new ProfileGuiCMD());
        } else {
            getCommand("profile").setExecutor(new ProfileCMD(clover));
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
        ProfileConfig.getInstance();
        FactionConfig.getInstance();
        RaceConfig.getInstance();
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

    public void InsertUserCharacteristics(Player player) {
        Player playername = player.getPlayer();
        int s = config.getInt("characteristics.strength");
        int c = config.getInt("characteristics.constitution");
        int w = config.getInt("characteristics.wisdom");
        int ch = config.getInt("characteristics.charisma");
        int i = config.getInt("characteristics.intelligence");
        int d = config.getInt("characteristics.dexterity");
        int l = config.getInt("characteristics.luck");
        db.setUserCharacteristics(playername, s, c, w, ch, i, d, l);
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs disable in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v." + pdFile.getVersion() + ")");

    }
}
