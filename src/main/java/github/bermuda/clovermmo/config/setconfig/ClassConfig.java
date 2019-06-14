package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

import java.util.Set;

public class ClassConfig extends ConfigLoader {

    private static ClassConfig Cconfig;

    private ClassConfig() {
        super("classes.yml");
    }

    public static ClassConfig getInstance() {
        if (Cconfig == null) {
            Cconfig = new ClassConfig();
        }
        return Cconfig;
    }

    @Override
    protected void loadKeys() {
        if (config.getConfigurationSection("ClassConfig") != null) {
            return;
        }
    }

    //classes
    public Set<String> getClassNames() {
        return config.getConfigurationSection("Classes").getKeys(false);
    }

    public int getClassStrength(String mclass) {
        return config.getInt("Classes." + mclass + ".strength");
    }

    public int getClassDexterity(String mclass) {
        return config.getInt("Classes." + mclass + ".dexterity");
    }

    public int getClassConstitution(String mclass) {
        return config.getInt("Classes." + mclass + ".constitution");
    }

    public int getClassIntelligence(String mclass) {
        return config.getInt("Classes." + mclass + ".intelligence");
    }

    public int getClassWisdom(String mclass) {
        return config.getInt("Classes." + mclass + ".wisdom");
    }

    public int getClassCharisma(String mclass) {
        return config.getInt("Classes." + mclass + ".charisma");
    }

    public int getClassLuck(String mclass) {
        return config.getInt("Classes." + mclass + ".luck");
    }

    //specs
    public Set<String> getSpecName(String mclass) {
        return config.getConfigurationSection("Classes." + mclass + ".Spec").getKeys(false);
    }

    public int getSpecStrength(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".strength");
    }

    public int getSpecDexterity(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".dexterity");
    }

    public int getSpecConstitution(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".constitution");
    }

    public int getSpecIntelligence(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".intelligence");
    }

    public int getSpecWisdom(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".wisdom");
    }

    public int getSpecCharisma(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".charisma");
    }

    public int getSpecLuck(String mclass, String spec) {
        return config.getInt("Classes." + mclass + ".Spec." + spec + ".luck");
    }

}