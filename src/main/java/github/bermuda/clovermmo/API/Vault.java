package github.bermuda.clovermmo.API;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.World;

public abstract class Vault extends Chat {

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


    @Override
    public void setGroupPrefix(String s, String s1, String s2) {

    }

    public String getPrimaryGroup(String world,
                                  org.bukkit.OfflinePlayer player) {
        return null;
    }

    abstract public String getGroupPrefix(String world, String group);

    public String getGroupPrefix(World world, String group) {
        return getGroupPrefix(world.getName(), group);
    }

    @Override
    public void setGroupSuffix(String s, String s1, String s2) {

    }

    @Override
    public int getGroupInfoInteger(String s, String s1, String s2, int i) {
        return 0;
    }

    @Override
    public void setGroupInfoInteger(String s, String s1, String s2, int i) {

    }

    @Override
    public double getGroupInfoDouble(String s, String s1, String s2, double v) {
        return 0;
    }

    @Override
    public void setGroupInfoDouble(String s, String s1, String s2, double v) {

    }

    @Override
    public boolean getGroupInfoBoolean(String s, String s1, String s2, boolean b) {
        return false;
    }

    @Override
    public void setGroupInfoBoolean(String s, String s1, String s2, boolean b) {

    }


    @Override
    public String getGroupInfoString(String s, String s1, String s2, String s3) {
        return null;
    }

    @Override
    public void setGroupInfoString(String s, String s1, String s2, String s3) {

    }
}


