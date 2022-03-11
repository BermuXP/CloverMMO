package bermuda.config;

import java.util.ArrayList;
import java.util.Set;

import static bermuda.MainCloverMMO.getPlugin;

public class BlacksmithConfig extends ConfigLoader {

    private static BlacksmithConfig config;

    private BlacksmithConfig() {
        super("professions", "blacksmith.yml");
    }

    public static BlacksmithConfig getInstance() {
        if (config == null) {
            config = new BlacksmithConfig();
        }
        return config;
    }

    @Override
    protected void loadKeys() {
        if (fileConfiguration.getConfigurationSection("BlacksmithConfig") != null) {
            return;
        }
    }

    /**
     * Load all the custom items
     * @return Set<String>      Set of strings.
     */
    public Set<String> getCustomItems() {
        return fileConfiguration.getConfigurationSection("customItems").getKeys(false);
    }

    public String getItem(String customItem) {
        return fileConfiguration.getString("customItems." + customItem + ".item");
    }

    public String getDisplayName(String customItem) {
        return fileConfiguration.getString("customItems." + customItem + ".name");
    }

    public String getLore(String customItem) {
        return fileConfiguration.getString("customItems." + customItem + ".lore");
    }

    public int getAmount(String customItem) {
        return fileConfiguration.getInt("customItems." + customItem + ".amount");
    }

    /**
     * Get all numbers from the
     * @param customItem
     * @return
     */
    public ArrayList<String> getAllNumberContent(String customItem) {
        ArrayList<String> allNumbers = new ArrayList<>();
        Set<String> allCustomItems = fileConfiguration.getConfigurationSection("customItems." + customItem).getKeys(false);
        getPlugin().getLogger().warning(String.valueOf(allCustomItems));
        for (String item : allCustomItems) {
            if(isNumeric(item) && Integer.parseInt(item) < 10) {
                allNumbers.add(fileConfiguration.getString("customItems." + customItem + "." +  item));
            }
        }
        return allNumbers;
    }

    private static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}