package github.bermuda.clovermmo.API;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
<<<<<<< HEAD

public class Vault extends Chat {
    
    public Vault(Permission perms) {
        super(perms);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Deprecated
    public String getPlayerPrefix(String s, String s1) {
        return null;
    }

    @Deprecated
    public void setPlayerPrefix(String s, String s1, String s2) {
    }

    @Deprecated
    public String getPlayerSuffix(String s, String s1) {
        return null;
    }

    @Deprecated
    public void setPlayerSuffix(String s, String s1, String s2) {
    }

    @Override
    public String getGroupPrefix(String s, String s1) {
        return null;
    }

    @Override
    public void setGroupPrefix(String s, String s1, String s2) {

    }

    @Override
    public String getGroupSuffix(String s, String s1) {
        return null;
    }

    @Override
    public void setGroupSuffix(String s, String s1, String s2) {
    }

    @Deprecated
    public int getPlayerInfoInteger(String s, String s1, String s2, int i) {
        return 0;
    }

    @Deprecated
    public void setPlayerInfoInteger(String s, String s1, String s2, int i) {
    }

    @Override
    public int getGroupInfoInteger(String s, String s1, String s2, int i) {
        return 0;
    }

    @Override
    public void setGroupInfoInteger(String s, String s1, String s2, int i) {
    }

    @Deprecated
    public double getPlayerInfoDouble(String s, String s1, String s2, double v) {
        return 0;
    }

    @Deprecated
    public void setPlayerInfoDouble(String s, String s1, String s2, double v) {
=======
import org.bukkit.World;
import org.bukkit.plugin.RegisteredServiceProvider;

import static org.bukkit.Bukkit.getServer;

public class Vault {

    public static Chat chat = null;

    public Vault(){
        setupChat();
>>>>>>> ee3b6ae04f8db72b997a9aa99316f848797b77ed
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

<<<<<<< HEAD
    @Override
    public void setGroupInfoDouble(String s, String s1, String s2, double v) {
    }

    @Deprecated
    public boolean getPlayerInfoBoolean(String s, String s1, String s2, boolean b) {
        return false;
    }

    @Deprecated
    public void setPlayerInfoBoolean(String s, String s1, String s2, boolean b) {

    }

    @Override
    public boolean getGroupInfoBoolean(String s, String s1, String s2, boolean b) {
        return false;
    }

    @Override
    public void setGroupInfoBoolean(String s, String s1, String s2, boolean b) {
    }

    @Deprecated
    public String getPlayerInfoString(String s, String s1, String s2, String s3) {
        return null;
    }

    @Deprecated
    public void setPlayerInfoString(String s, String s1, String s2, String s3) {
    }

    @Override
    public String getGroupInfoString(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public void setGroupInfoString(String s, String s1, String s2, String s3) {
    }
=======
    public static Chat getChat() {
        return chat;
    }

>>>>>>> ee3b6ae04f8db72b997a9aa99316f848797b77ed
}


