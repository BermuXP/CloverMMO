package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class ProfileConfig extends ConfigLoader {

    private static ProfileConfig Pconfig;

    private ProfileConfig() {
        super("profile.yml");
    }

    public static ProfileConfig getInstance() {
        if (Pconfig == null) {
            Pconfig = new ProfileConfig();
        }
        return Pconfig;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("ProfileConfig") != null) {
            return;
        }
    }
}
