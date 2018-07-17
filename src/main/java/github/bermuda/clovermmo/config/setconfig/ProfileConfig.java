package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class ProfileConfig extends ConfigLoader {

    private static ProfileConfig instance;

    private ProfileConfig() {
        super("profile.yml");
    }

    public static ProfileConfig getInstance() {
        if (instance == null) {
            instance = new ProfileConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("ProfileConfig") != null) {
            return;
        }
    }
}
