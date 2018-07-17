package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class ClassConfig extends ConfigLoader {

    private static ClassConfig instance;

    private ClassConfig() {
        super("classes.yml");
    }

    public static ClassConfig getInstance() {
        if (instance == null) {
            instance = new ClassConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("ClassConfig") != null) {
            return;
        }
    }
}