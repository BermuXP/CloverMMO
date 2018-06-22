package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.config.scoreboarConfig;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.commands.RaceCommand;

import github.bermuda.clovermmo.database.SQLite;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
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
    public String cloverprefix = ChatColor.BOLD +""+ ChatColor.GREEN + "CloverMMO " + ChatColor.BLUE + "» " + ChatColor.WHITE;

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
        getCommand("level").setExecutor(new LevelCommand());
        getCommand("cloverboard").setExecutor(new CloverboardCommand());
        getCommand("clovermmo").setExecutor(new ClovermmoCommand());
        getCommand("race").setExecutor(new RaceCommand(clover));
        getCommand("class").setExecutor(new ClassCommand(clover));
        getCommand("profile").setExecutor(new ProfileCommand());
    }

    private void configbasic() {
        List<String> races = Arrays.asList("Ginger", "Monkey", "Elf", "Human");
        config.set("races", races);
        List<String> classes = Arrays.asList("Paladin", "Druid");
        config.set("classes", classes);
        config.set("profile.race", true);
        config.set("profile.rankandusername", true);
        config.set("profile.seperateusername", false);
        config.set("profile.seperaterank", false);
        config.set("profile.level", true);
        config.set("profile.exp", true);
        config.set("profile.maxhp", true);
        clover.saveConfig();
    }

    public Database getRDatabase() {
        return this.db;
    }

    private void loadConfigFiles() {
        scoreboarConfig.getInstance();
    }

    public void debug(String message) {
        getLogger().info("Debug >> " + message);
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        String playername = event.getPlayer().getName();
        if (!event.getPlayer().hasPlayedBefore()) {
            event.getPlayer().sendMessage("Welcome " + playername + ", it's your first time here... to start you need to pick a race! what race are you?");
        } else {
            event.getPlayer().sendMessage("Welcome back " + playername);
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
