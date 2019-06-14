package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;
import java.util.List;
import java.util.Set;

public class ProfileConfig extends ConfigLoader {

    private static ProfileConfig Pconfig;

    private ProfileConfig() {
        super("profile.yml");
    }

    public static ProfileConfig profile() {
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

    public int getExp(int lvl) {
        return config.getInt("Profile.level." + lvl + ".NextRankExp");
    }

    public int getPoints(int lvl) {
        return config.getInt("Profile.level." + lvl + ".points");
    }

    public List<String> getProfileChatDisplay() {
        return config.getStringList("Profile.chat");
    }

    public Set<String> getGUIKeys() {
        return config.getConfigurationSection("Profile.gui.content.").getKeys(false);
    }

    public String getGUIName() {
        return config.getString("Profile.gui.title");
    }

    public String getGUIItem(String number) {
        return config.getString("Profile.gui.content." + number + ".item");
    }

    public String getGUIDisplayname(String number) {
        return config.getString("Profile.gui.content." + number + ".displayname");
    }

    public List<String> getGUILore(String number) {
        return config.getStringList("Profile.gui.content." + number + ".description");
    }

    public int getGUISpot(String number) {
        return config.getInt("Profile.gui.content." + number + ".spot");
    }
}
