package bermuda;

import bermuda.commands.*;
import bermuda.config.ClassConfig;
import bermuda.config.DefaultConfig;
import bermuda.config.RaceConfig;
import bermuda.database.managers.DbHelper;
import bermuda.database.managers.DbManager;
import bermuda.database.models.ClassModel;
import bermuda.database.models.FactionModel;
import bermuda.database.models.RacesModel;
import bermuda.database.models.StatsModel;
import bermuda.listerners.EntityListener;
import bermuda.listerners.PlayerListener;
import bermuda.listerners.UiListener;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.BukkitLocales;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

import static bermuda.types.StatTypes.class_type;
import static bermuda.types.StatTypes.race_type;

public class MainCloverMMO extends JavaPlugin implements Listener {
    private static Plugin plugin;
    private static BukkitCommandManager manager;
    private HashMap<String, RacesModel> races;
    private HashMap<Integer, StatsModel> stats;
    private HashMap<String, ClassModel> classes;
    private HashMap<String, FactionModel> factions;
    private static DbHelper dbHelper;

    public void onEnable() {
        plugin = this;
        Locale.setDefault(new Locale("en", "US"));
        startupConfigCalls();
        events();
        startupDatabaseCalls();
        commands();
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();
        registerHealthBar(sb);
        registerNameTag(sb);
    }

    /**
     * When the plugin gets disabled call this
     * plugin gets set to 0 for memory purposes.
     */
    public void onDisable() {
        plugin = null;
    }

    public static DbHelper getDbHelper() {
        return dbHelper;
    }

    /**
     * All the events.
     */
    private void events() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new UiListener(), this);
        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new EntityListener(), this);
    }

    /**
     * when the plugin starts this gets called to see if all the configs are set if not, set them.
     */
    private void startupConfigCalls() {
        dbHelper = DbManager.newDbManager(DefaultConfig.getInstance().getDatabaseEnabled().toLowerCase());

        dbHelper.createTables();
        Set<String> races = RaceConfig.getInstance().getRaceNames();
        HashMap<String, Integer> statItem = dbHelper.setAllConfigStatsByType(races, race_type);
        dbHelper.setAllConfigRaces(races, statItem);

        Set<String> classes = ClassConfig.getInstance().getClassNames();
        HashMap<String, Integer> classStatItem = dbHelper.setAllConfigStatsByType(classes, class_type);
        dbHelper.setAllConfigClasses(classes, classStatItem);

//        BlacksmithConfig bsc = BlacksmithConfig.getInstance();
//
//        Set<String> customItems = bsc.getCustomItems();
//        Blacksmith items = new Blacksmith();
//        for (String item : customItems) {
//            ArrayList<String> allNumberContent = bsc.getAllNumberContent(item);
//
//            String displayName = bsc.getDisplayName(item);
//            String lore = bsc.getLore(item);
//            int amount = bsc.getAmount(item);
//            Material materialcheck = Material.matchMaterial(bsc.getItem(item));
//            getPlugin().getLogger().warning(String.valueOf(materialcheck));
//            if (materialcheck != null) {
//                ItemStack citem = new ItemStack(materialcheck, amount);
//                items.customRecipe(citem, displayName, lore, allNumberContent);
//            } else {
//                getPlugin().getLogger().warning("Item material for custom item " + displayName + " - [" + bsc.getItem(item) + "] does not exist.");
//            }
//        }
    }

    /**
     * checks if the database exists, if not make one.
     */
    private void startupDatabaseCalls() {
        races = dbHelper.getAllRaces();
        classes = dbHelper.getAllClasses();
        stats = dbHelper.getAllStats(null);
        factions = dbHelper.getAllFactions();
    }

    /**
     * Get a instance of the plugin to use in different files
     *
     * @return Plugin
     */
    public static Plugin getPlugin() {
        return plugin;
    }

    public static BukkitCommandManager getManager() {
        return manager;
    }

    public static DbHelper getDbhelper() {
        return dbHelper;
    }

    /**
     * All the commands
     */
    private void commands() {
        manager = new BukkitCommandManager(plugin);
        BukkitLocales locals = manager.getLocales();
        manager.usePerIssuerLocale(true, false);
        manager.addSupportedLanguage(Locale.ENGLISH);
        locals.setDefaultLocale(Locale.ENGLISH);
        //do not remove, this is a beta API not deprecated.
        manager.enableUnstableAPI("help");

        manager.registerCommand(new DungeonCommand());
        manager.registerCommand(new CloverCommand());
        manager.registerCommand(new CloverBoardCommand());
        manager.registerCommand(new ProfileCommand());
        manager.registerCommand(new RaceCommand(races, stats));
        //todo only send the stats with a certain type
        manager.registerCommand(new ClassCommand(classes, stats));
        manager.registerCommand(new FactionCommand(factions));
    }

    public void registerHealthBar(Scoreboard sb) {
        if(sb.getObjective("health") != null) {
            sb.getObjective("health").unregister();
        }
        Objective object = sb.registerNewObjective("health", "health", "health", RenderType.HEARTS);
//        object.setDisplayName();
    }

    public void registerNameTag(Scoreboard sb) {
        if(sb.getTeam("blue") != null) {
            sb.getTeam("blue").unregister();
        }
        Team t = sb.registerNewTeam("blue");
        t.setPrefix(ChatColor.BLUE + "");
    }

}

