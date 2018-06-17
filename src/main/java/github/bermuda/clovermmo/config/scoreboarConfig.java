package github.bermuda.clovermmo.config;

public class scoreboarConfig extends configLoader {

    private static scoreboarConfig instance;


    private scoreboarConfig() {
        super("scoreboard.yml");
    }

    public static scoreboarConfig getInstance() {
        if (instance == null) {
            instance = new scoreboarConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("scoreboarConfig") != null) {
            backup();
            return;
        }
    }
}