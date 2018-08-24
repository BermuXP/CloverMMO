package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

import java.util.List;
import java.util.Set;

public class RaceConfig extends ConfigLoader {

    private static RaceConfig Rconfig;

    private RaceConfig() {
        super("races.yml");
    }

    public static RaceConfig getInstance() {
        if (Rconfig == null) {
            Rconfig = new RaceConfig();
        }
        return Rconfig;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("RaceConfig") != null) {
            return;
        }
    }

    public Set<String> getRaceNames() {
        return config.getConfigurationSection("Races").getKeys(false);
    }

    public int getRaceStrength(String race) {
        return config.getInt("Races." + race + ".strength");
    }

    public int getRaceDexterity(String race) {
        return config.getInt("Races." + race + ".dexterity");
    }

    public int getRaceConstitution(String race) {
        return config.getInt("Races." + race + ".constitution");
    }

    public int getRaceIntelligence(String race) {
        return config.getInt("Races." + race + ".intelligence");
    }

    public int getRaceWisdom(String race) {
        return config.getInt("Races." + race + ".wisdom");
    }

    public int getRaceCharisma(String race) {
        return config.getInt("Races." + race + ".charisma");
    }

    public int getRaceLuck(String race) {
        return config.getInt("Races." + race + ".luck");
    }

    public Set<String> getGuiRaceNames() {
        return config.getConfigurationSection("gui.content").getKeys(false);
    }

    public String getRaceItem(String race) {
        return config.getString("gui.content." + race + ".item");
    }

    public List<String> getGuiRaceLore(String race) {
        return config.getStringList("gui.content." + race + ".description");
    }

    public int getAddPointsOnRaceSelect() {
        return config.getInt("AddPointsOnRaceSelect");
    }

    public List<String> getGuiLore() {
        return config.getStringList("gui.description");
    }

    public String getGuiTitle() {
        return config.getString("gui.title");
    }

    public int getGuiRows() {
        return config.getInt("gui.rows");
    }

    public int getGuiRaceSpot(String race) {
        return config.getInt("gui.content." + race + ".spot");
    }
}