package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

import java.util.ArrayList;
import java.util.List;

public class AdvancedConfig extends ConfigLoader {

    private static AdvancedConfig Aconfig;

    private AdvancedConfig() {
        super("advanced.yml");
    }

    public static AdvancedConfig advanced() {
        if (Aconfig == null) {
            Aconfig = new AdvancedConfig();
        }
        return Aconfig;
    }

    @Override
    protected boolean validateKeys() {
        List<String> reason = new ArrayList<>();
        return noErrorsInConfig(reason);
    }

    @Override
    protected void loadKeys() { }

    public int getDifficultyExp(int d) {
        return config.getInt("Difficulty." + d + ".exp", 0);
    }

    public String getMobsCostumName(String mob) {
        return config.getString("Mobs." + mob + ".CustomName", mob);
    }

    public int getMobDifficulty(String mob) {
        return config.getInt("Mobs." + mob + ".Difficulty", 1);
    }
}
