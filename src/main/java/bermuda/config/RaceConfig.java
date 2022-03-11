package bermuda.config;


import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class RaceConfig extends ConfigLoader {

    private static RaceConfig raceConfig;

    public RaceConfig() {
        super("races.yml");
    }

    public static RaceConfig getInstance() {
        if (raceConfig == null) {
            raceConfig = new RaceConfig();
        }
        return raceConfig;
    }

    @Override
    protected void loadKeys() {
        if (fileConfiguration.getConfigurationSection("RaceConfig") != null) {
            return;
        }
    }

    /**
     * get all the race names from the races config file
     * @return
     */
    public Set<String> getRaceNames() {
        return fileConfiguration.getConfigurationSection("Races").getKeys(false);
    }

    /**
     * get all the race stats from a sepecifc race
     * @param race
     * @return
     */
    public HashMap<String, Integer> getRaceStats(String race) {
        HashMap<String, Integer> raceStats = new HashMap<>();
        raceStats.put("strength", fileConfiguration.getInt("Races." + race + ".strength"));
        raceStats.put("dexterity", fileConfiguration.getInt("Races." + race + ".dexterity"));
        raceStats.put("constitution", fileConfiguration.getInt("Races." + race + ".constitution"));
        raceStats.put("intelligence", fileConfiguration.getInt("Races." + race + ".intelligence"));
        raceStats.put("wisdom", fileConfiguration.getInt("Races." + race + ".wisdom"));
        raceStats.put("charisma", fileConfiguration.getInt("Races." + race + ".charisma"));
        raceStats.put("luck", fileConfiguration.getInt("Races." + race + ".luck"));
        return raceStats;
    }

    public String getRaceDescription(String race) {
        return fileConfiguration.getString("Races." + race + ".description");
    }

    public int getRaceStrength(String race) {
        return fileConfiguration.getInt("Races." + race + ".strength");
    }

    public int getRaceDexterity(String race) {
        return fileConfiguration.getInt("Races." + race + ".dexterity");
    }

    public int getRaceConstitution(String race) {
        return fileConfiguration.getInt("Races." + race + ".constitution");
    }

    public int getRaceIntelligence(String race) {
        return fileConfiguration.getInt("Races." + race + ".intelligence");
    }

    public int getRaceWisdom(String race) {
        return fileConfiguration.getInt("Races." + race + ".wisdom");
    }

    public int getRaceCharisma(String race) {
        return fileConfiguration.getInt("Races." + race + ".charisma");
    }

    public int getRaceLuck(String race) {
        return fileConfiguration.getInt("Races." + race + ".luck");
    }

    public int getAddPointsOnRaceSelect() {
        return fileConfiguration.getInt("AddPointsOnRaceSelect");
    }

    public List<String> getGuiLore() {
        return fileConfiguration.getStringList("gui.description");
    }

    public String getGuiTitle() {
        return fileConfiguration.getString("gui.title");
    }

    public int getGuiRows() {
        return fileConfiguration.getInt("gui.rows");
    }

    public int getGuiRaceSpot(String race) {
        return fileConfiguration.getInt("gui.content." + race + ".spot");
    }
}