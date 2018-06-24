package github.bermuda.clovermmo.config;

public class specConfig extends configLoader {

    private static specConfig instance;


    private specConfig() {
        super("spec.yml");
    }

    public static specConfig getInstance() {
        if (instance == null) {
            instance = new specConfig();
        }
        return instance;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("specConfig") != null) {
            backup();
            return;
        }
    }
}