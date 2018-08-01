package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

import java.util.Set;

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

//    public Set<String> getLevels() {
//        return config.getConfigurationSection("Profile.level").getKeys(false);
//    }

    public int getExp(int lvl) {
        return config.getInt("Profile.level." + lvl + ".NextRankExp");
    }

    public int getPoints(int lvl) {
        return config.getInt("Profile.level." + lvl + ".points");
    }

    public int getLevels(int lvl) {
        return config.getInt("Profile.level." + lvl);
    }
}
