package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.config.classConfig;
import github.bermuda.clovermmo.config.raceConfig;
import github.bermuda.clovermmo.config.scoreboardConfig;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.commands.RaceCommand;

import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {

    public static CloverMMO clover;
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
        configbasic();
        PluginDescriptionFile pdFile = getDescription();
        loadConfigFiles();
        //logs startup in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been enabled (v." + pdFile.getVersion() + ")");
        getServer().getPluginManager().registerEvents(clover, clover);
        //gets the commands from the commands folder.
        commands();
    }

    private void commands() {
//        String cmmo = "cmmo";
        getCommand("clovermmo").setExecutor(new ClovermmoCommand());
        getCommand("level").setExecutor(new LevelCommand());
        getCommand("cloverboard").setExecutor(new CloverboardCommand());
        getCommand("race").setExecutor(new RaceCommand(clover));
        getCommand("class").setExecutor(new ClassCommand(clover));
        getCommand("profile").setExecutor(new ProfileCommand(clover));
    }

    private void configbasic() {
        List<String> races = Arrays.asList("Asatha", "Aasimar", "Tiefling", "Ifrit", "Halfling", "Human", "Oread", "Sylph", "Undine", "Halfling", "Half-elf", "Elf", "Dwarf", "Orc", "Half-orc", "Tengu");

        for (String r : races) {
            config.addDefault("races." + r + ".maxhp", "");
            config.addDefault("races." + r + ".hpregen", "");
            config.addDefault("races." + r + ".armor", "");
            config.addDefault("races." + r + ".armorpenetration", "");
            config.addDefault("races." + r + ".handdamage", "");
            config.addDefault("races." + r + ".bowdamage",  "");
            config.addDefault("races." + r + ".sworddamage", "");
            config.addDefault("races." + r + ".axedamage", "");
//            config.addDefault("races", races);

            clover.db = new SQLite(clover);
            clover.db.load();
            db.setDatabaseRaces(r);
        }

        List<String> classes = Arrays.asList("Paladin", "Druid", "Priest", "Hunter", "Mage", "Necromancer", "Warrior", "Thief");

        for (String s : classes) {
            List<String> randomnames = Arrays.asList("wowaspecname", "wowasecondspecname", "evenathirdspecname");
            config.addDefault("classes." + s + ".spec.name", randomnames);
//            for (String random : randomnames) {
//                clover.db = new SQLite(clover);
//                clover.db.load();
//                db.setDatabaseSpecNames(s, random);
//            }

            config.addDefault("classes." + s + ".maxhp", 20);
            config.addDefault("classes." + s + ".hpregen", "");
            config.addDefault("classes." + s + ".armor", "");
            config.addDefault("classes." + s + ".armorpenetration", "");
            config.addDefault("classes." + s + ".handdamage", "");
            config.addDefault("classes." + s + ".bowdamage", "");
            config.addDefault("classes." + s + ".sworddamage", "");
            config.addDefault("classes." + s + ".axedamage", "");

            config.addDefault("classes." + s + ".spec.maxhp", "");
            config.addDefault("classes." + s + ".spec.hpregen", "");
            config.addDefault("classes." + s + ".spec.armor", "");
            config.addDefault("classes." + s + ".spec.armorpenetration", "");
            config.addDefault("classes." + s + ".spec.handdamage", "");
            config.addDefault("classes." + s + ".spec.bowdamage", "");
            config.addDefault("classes." + s + ".spec.sworddamage", "");
            config.addDefault("classes." + s + ".spec.axedamage", "");

            clover.db = new SQLite(clover);
            clover.db.load();
            db.setDatabaseClasses(s);
        }

        config.addDefault("profile.race", true);
        config.addDefault("profile.classes", true);
        config.addDefault("profile.rankandusername", true);
        config.addDefault("profile.seperateusername", false);
        config.addDefault("profile.seperaterank", false);
        config.addDefault("profile.level", true);
        config.addDefault("profile.exp", true);
        config.addDefault("profile.maxhp", true);
        config.addDefault("profile.spec", true);
        config.addDefault("profile.point", true);
        config.addDefault("Onjoin.OnFirstJoinMessageEnable", true);
        config.addDefault("Onjoin.OnReturningJoinMessageEnable", true);
        config.addDefault("Onjoin.AddPointsOnJoin", 6);
        config.options().copyDefaults(true);
        saveConfig();
    }

    public Database getRDatabase() {
        return this.db;
    }

    private void loadConfigFiles() {
        scoreboardConfig.getInstance();
        raceConfig.getInstance();
        classConfig.getInstance();
    }

    public void debug(String message) {
        getLogger().info("Debug >> " + message);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        String playername = event.getPlayer().getName();
        if (event.getPlayer().hasPlayedBefore()) {
            if(clover.config.get("Onjoin.OnFirstJoinMessage").equals(true)) {
                event.getPlayer().sendMessage("Welcome back " + playername);
            }
        } else {
            db.addpoints(clover.config.getInt("Onjoin.AddPointsOnJoin"), playername);
            if (clover.config.get("Onjoin.OnFirstJoinMessage").equals(true)) {
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
