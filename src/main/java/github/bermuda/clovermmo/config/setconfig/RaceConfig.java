package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class RaceConfig extends ConfigLoader {

    private static RaceConfig instance;

    private RaceConfig() {
        super("races.yml");
    }

    public static RaceConfig getInstance() {
        if (instance == null) {
            instance = new RaceConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("RaceConfig") != null) {
            return;
        }
    }
}