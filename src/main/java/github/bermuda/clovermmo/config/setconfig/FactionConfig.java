package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class FactionConfig extends ConfigLoader {

    private static FactionConfig instance;

    private FactionConfig() {
        super("factions.yml");
    }

    public static FactionConfig getInstance() {
        if (instance == null) {
            instance = new FactionConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("FactionConfig") != null) {
            return;
        }
    }
}
