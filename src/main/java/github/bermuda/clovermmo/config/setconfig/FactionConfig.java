package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class FactionConfig extends ConfigLoader {

    private static FactionConfig Fconfig;

    private FactionConfig() {
        super("factions.yml");
    }

    public static FactionConfig getInstance() {
        if (Fconfig == null) {
            Fconfig = new FactionConfig();
        }
        return Fconfig;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("FactionConfig") != null) {
            return;
        }
    }
}
