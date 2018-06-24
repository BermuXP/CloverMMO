package github.bermuda.clovermmo.config;

public class raceConfig extends configLoader {

    private static raceConfig instance;

    private raceConfig() {
        super("race.yml");
    }

    public static raceConfig getInstance() {
        if (instance == null) {
            instance = new raceConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("raceConfig") != null) {
            backup();
            return;
        }
    }
}