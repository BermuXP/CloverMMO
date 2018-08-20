package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.config.ConfigLoader;

public class DefaultConfig extends ConfigLoader {

    private static DefaultConfig Dconfig;

    private DefaultConfig() {
        super("config.yml");
    }

    public static DefaultConfig config() {
        if (Dconfig == null) {
            Dconfig = new DefaultConfig();
        }
        return Dconfig;
    }

    @Override
    protected void loadKeys() { }

    public boolean getOnFirstJoin() {
        return config.getBoolean("onjoin.OnFirstJoinMessageEnable");
    }

    public boolean getOnReturnJoin() {
        return config.getBoolean("onjoin.OnReturningJoinMessageEnable");
    }

    public String getGuiOrChatProfile() {
        return config.getString("GuiOrChat.Profile");
    }

    public String getGuiOrChatClass() {
        return config.getString("GuiOrChat.Class");
    }

    public String getGuiOrChatRace() {
        return config.getString("GuiOrChat.Race");
    }
}

