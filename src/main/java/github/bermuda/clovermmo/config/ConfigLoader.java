package github.bermuda.clovermmo.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import static github.bermuda.clovermmo.CloverMMO.clover;

public abstract class ConfigLoader {
    protected String fileName;
    private File configFile;
    protected FileConfiguration config;

    public ConfigLoader(String relativePath, String fileName) {
        this.fileName = fileName;
        configFile = new File(clover.getDataFolder(), relativePath + File.separator + fileName);
        loadFile();
    }

    public ConfigLoader(String fileName) {
        this.fileName = fileName;
        configFile = new File(clover.getDataFolder(), fileName);
        loadFile();
    }

    protected void loadFile() {
        if (!configFile.exists()) {
            clover.debug("Creating " + fileName + " File...");
            try {
                clover.saveResource(fileName, false); // Normal files
            } catch (IllegalArgumentException ex) {
                clover.saveResource(configFile.getParentFile().getName() + File.separator + fileName, false); // Mod files
            }
        } else {
            clover.debug("Loading CloverMMO " + fileName + " File...");
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    protected abstract void loadKeys();

    protected boolean validateKeys() {
        return true;
    }

//    protected boolean noErrorsInConfig(List<String> issues) {
//        for (String issue : issues) {
//            plugin.getLogger().warning(issue);
//        }
//
//        return issues.isEmpty();
//    }

    protected void validate() {
        if (validateKeys()) {
            clover.debug("No errors found in " + fileName + "!");
        } else {
            clover.getLogger().warning("Errors were found in " + fileName + "! CloverMMO was disabled!");
            clover.getServer().getPluginManager().disablePlugin(clover);
            clover.noErrorsInConfigFiles = false;
        }
    }

    public File getFile() {
        return configFile;
    }

    public void backup() {
        clover.getLogger().warning("You are using an old version of the " + fileName + " file.");
        clover.getLogger().warning("Your old file has been renamed to " + fileName + ".old and has been replaced by an updated version.");
        configFile.renameTo(new File(configFile.getPath() + ".old"));

        if (clover.getResource(fileName) != null) {
            clover.saveResource(fileName, true);
        }

        clover.getLogger().warning("Reloading " + fileName + " with new values...");
        loadFile();
        loadKeys();
    }
}
