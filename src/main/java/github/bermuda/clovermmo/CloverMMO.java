package github.bermuda.clovermmo;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CloverMMO extends JavaPlugin implements Listener {

    public static CloverMMO clover;
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        clover = this;
        config.addDefault("test", true);
        config.options().copyDefaults(true);
        saveConfig();

        getLogger().info("CloverMMO has been enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }



    @Override
    public void onDisable() {
        getLogger().info("CloverMMO has been disabled");
    }
}
