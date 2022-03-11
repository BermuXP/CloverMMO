package bermuda.config;

import java.util.Set;

public class ClassConfig extends ConfigLoader {

    private static ClassConfig classConfig;

    private ClassConfig() {
        super("classes.yml");
    }

    public static ClassConfig getInstance() {
        if (classConfig == null) {
            classConfig = new ClassConfig();
        }
        return classConfig;
    }

    @Override
    protected void loadKeys() {
        if (fileConfiguration.getConfigurationSection("ClassConfig") != null) {
            return;
        }
    }

    //classes
    public Set<String> getClassNames() {
        return fileConfiguration.getConfigurationSection("Classes").getKeys(false);
    }

    public Set<String> getSubClassNames(String mClass) {
        return fileConfiguration.getConfigurationSection("Classes." + mClass + ".Spec").getKeys(false);
    }

    public int getClassStrength(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".strength");
    }

    public int getClassDexterity(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".dexterity");
    }

    public int getClassConstitution(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".constitution");
    }

    public int getClassIntelligence(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".intelligence");
    }

    public int getClassWisdom(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".wisdom");
    }

    public int getClassCharisma(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".charisma");
    }

    public int getClassLuck(String mclass) {
        return fileConfiguration.getInt("Classes." + mclass + ".luck");
    }

    //specs
    public Set<String> getSpecName(String mclass) {
        return fileConfiguration.getConfigurationSection("Classes." + mclass + ".Spec").getKeys(false);
    }

    public int getSpecStrength(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".strength");
    }

    public int getSpecDexterity(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".dexterity");
    }

    public int getSpecConstitution(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".constitution");
    }

    public int getSpecIntelligence(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".intelligence");
    }

    public int getSpecWisdom(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".wisdom");
    }

    public int getSpecCharisma(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".charisma");
    }

    public String getClassDescription(String mclass) {
        return fileConfiguration.getString("Classes." + mclass + ".description");
    }

    public String getSpecDescription(String mClass, String spec) {
        return fileConfiguration.getString("Classes." + mClass + ".Spec." + spec + ".description");
    }

    public int getSpecLuck(String mclass, String spec) {
        return fileConfiguration.getInt("Classes." + mclass + ".Spec." + spec + ".luck");
    }

}