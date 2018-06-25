package github.bermuda.clovermmo.config;

public class scoreboardConfig extends configLoader {

    private static scoreboardConfig instance;


    private scoreboardConfig() {
        super("scoreboard.yml");
    }

    public static scoreboardConfig getInstance() {
        if (instance == null) {
            instance = new scoreboardConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("scoreboardConfig") != null) {
            backup();
            return;
        }
    }
}