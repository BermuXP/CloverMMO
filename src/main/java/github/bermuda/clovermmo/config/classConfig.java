package github.bermuda.clovermmo.config;

public class classConfig extends configLoader {

    private static classConfig instance;

    private classConfig() {
        super("classes.yml");
    }

    public static classConfig getInstance() {
        if (instance == null) {
            instance = new classConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("classConfig") != null) {
            backup();
            return;
        }
    }
}