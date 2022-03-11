package bermuda.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

import static bermuda.MainCloverMMO.getPlugin;

public abstract class ConfigLoader {
    protected String fileName;
    private File configFile;
    protected FileConfiguration fileConfiguration;

    /**
     * Load config file from a specific path.
     * @param relativePath  string   path
     * @param fileName      string   file name
     */
    public ConfigLoader(String relativePath, String fileName) {
        this.fileName = fileName;
        configFile = new File(getPlugin().getDataFolder(), relativePath + File.separator + fileName);
        loadFile();
    }

    /**
     * Load config file by name.
     * @param fileName      string       file name
     */
    public ConfigLoader(String fileName) {
        this.fileName = fileName;
        configFile = new File(getPlugin().getDataFolder(), fileName);
        loadFile();
    }

    protected void loadFile() {
        if (!configFile.exists()) {
            getPlugin().getLogger().warning("Creating " + fileName + " File...");
            try {
                getPlugin().saveResource(fileName, false);
            } catch (IllegalArgumentException ex) {
                getPlugin().saveResource(configFile.getParentFile().getName() + File.separator + fileName, false); // Mod files
            }
        } else {
            getPlugin().getLogger().warning("Loading " + fileName + " File...");
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(configFile);
    }

    protected abstract void loadKeys();

//    protected boolean validateKeys() {
//        return true;
//    }

    protected boolean noErrorsInConfig(List<String> issues) {
        for (String issue : issues) {
            getPlugin().getLogger().warning(issue);
        }
        return issues.isEmpty();
    }


//    protected void validate() {
//        if (validateKeys()) {
//            getPlugin().getLogger().warning("No errors found in " + fileName + "!");
//        } else {
//            getPlugin().getLogger().warning("Errors were found in " + fileName + "! CloverMMO was disabled!");
//            getPlugin().getServer().getPluginManager().disablePlugin(getPlugin());
////            bermuda.MainCloverMMO.clover.noErrorsInConfigFiles = false;
//        }
//    }

    public File getFile() {
        return configFile;
    }

}
