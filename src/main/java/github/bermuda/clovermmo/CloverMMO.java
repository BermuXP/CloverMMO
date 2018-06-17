package github.bermuda.clovermmo;

import github.bermuda.clovermmo.commands.*;

import github.bermuda.clovermmo.config.scoreboarConfig;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CloverMMO extends JavaPlugin implements Listener {

    public static CloverMMO clover;
    public boolean noErrorsInConfigFiles = true;
    FileConfiguration config = getConfig();
    public static Chat chat = null;

    @Override
    public void onEnable() {
        PluginDescriptionFile pdFile = getDescription();
        clover = this;
        config.addDefault("text", true);
        config.options().copyDefaults(true);
        saveConfig();
        loadConfigFiles();
        //logs startup in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been enabled (v."  + pdFile.getVersion() + ")");
        getServer().getPluginManager().registerEvents(this, this);
        //gets the commands from the commands folder.
        getCommand("level").setExecutor(new level());
        getCommand("rank").setExecutor(new rank());
        getCommand("profile").setExecutor(new profile());
        getCommand("cloverboard").setExecutor(new cloverboard());
        getCommand("clovermmo").setExecutor(new clovermmo());
    }

    private void loadConfigFiles() {
        scoreboarConfig.getInstance();
    }

    public void debug(String message) {
        getLogger().info("[Debug] " + message);
    }

//    @EventHandler
//    public void onPlayerJoin(PlayerJoinEvent e){
// }
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdFile = getDescription();
        //logs disable in the console.
        Logger logger = getLogger();
        logger.info(pdFile.getName() + " has been disabled (v."  + pdFile.getVersion() + ")");

    }
}
