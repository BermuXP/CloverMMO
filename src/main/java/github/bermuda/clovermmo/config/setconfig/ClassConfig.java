package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class ClassConfig extends ConfigLoader {

    private static ClassConfig Cconfig;

    private ClassConfig() {
        super("classes.yml");
    }

    public static ClassConfig getInstance() {
        if (Cconfig == null) {
            Cconfig = new ClassConfig();
        }
        return Cconfig;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("ClassConfig") != null) {
            return;
        }
    }
}