package bermuda.config;

public class DefaultConfig extends ConfigLoader {

    private static DefaultConfig config;

    private DefaultConfig() {
        super("config.yml");
    }

    public static DefaultConfig getInstance() {
        if (config == null) {
            config = new DefaultConfig();
        }
        return config;
    }

    @Override
    protected void loadKeys() { }

    public int getUnknownConfigInt(String configString) {
        return fileConfiguration.getInt(configString);
    }

    public String getPluginPrefix() {
        return fileConfiguration.getString("Prefix");
    }

    public String getLocale() { return fileConfiguration.getString("Locale", "en_us"); }

    public String getDatabaseEnabled() {
        return fileConfiguration.getString("Database.enabled", "sqlite");
    }

    public boolean getOnFirstJoin() {
        return fileConfiguration.getBoolean("onjoin.OnFirstJoinMessageEnable");
    }

    public boolean getOnReturnJoin() {
        return fileConfiguration.getBoolean("onjoin.OnReturningJoinMessageEnable");
    }

    public String getGuiOrChatProfile() {
        return fileConfiguration.getString("GuiOrChat.Profile");
    }

    public String getGuiOrChatClass() {
        return fileConfiguration.getString("GuiOrChat.class");
    }

    public String getGuiOrChatRace() {
        return fileConfiguration.getString("GuiOrChat.Race");
    }
}

