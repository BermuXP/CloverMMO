package bermuda.database.models;

import bermuda.MainCloverMMO;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ProfileModel extends model {
    private int id;
    private String name;
    private String description;
    private int selected;
    private int privacy;
    private String uuid;
    private int race_id;
    private int faction_id;
    private int class_id;
    private int sub_class_id;
    private int stats_id;
    private int point;
    private int exp;

    private static HashMap<String, ProfileModel> allPlayers = new HashMap<>();

    public ProfileModel() {}

    public ProfileModel(int id, String uuid, int points, int exp) {
        this.id = id;
        this.uuid = uuid;
        this.point = points;
        this.exp = exp;
    }

    /**
     * foreach profile items add to profile model.
     *
     * @param profileData
     */
    public ProfileModel(Map<String, String> profileData) {
        this.id = Integer.parseInt(profileData.get("id"));
        this.name = profileData.get("name");
        if(profileData.get("description") != null) {
            this.description = profileData.get("description");
        }
        if(profileData.get("selected") != null) {
            this.selected = Integer.parseInt(profileData.get("selected"));
        }
        if(profileData.get("privacy") != null) {
            this.privacy = Integer.parseInt(profileData.get("privacy"));
        }
        this.uuid = profileData.get("uuid");
        if(profileData.get("race_id") != null) {
            this.race_id = Integer.parseInt(profileData.get("race_id"));
        }
        if(profileData.get("faction_id") != null) {
            this.faction_id = Integer.parseInt(profileData.get("faction_id"));
        }
        if(profileData.get("class_id") != null) {
            this.class_id = Integer.parseInt(profileData.get("class_id"));
        }
        if(profileData.get("sub_class_id") != null) {
            this.sub_class_id = Integer.parseInt(profileData.get("sub_class_id"));
        }
        if(profileData.get("stats_id") != null) {
            this.stats_id = Integer.parseInt(profileData.get("stats_id"));
        }
        this.point = Integer.parseInt(profileData.get("point"));
        this.exp = Integer.parseInt(profileData.get("exp"));
    }

    /**
     * Add a new player
     *
     * @param player Player      The player that needs to be saved.
     */
    public void addPlayer(Player player) {
        if (!allPlayers.containsKey(player.getName())) {
            ProfileModel p = null;
            HashMap<String, String> hm = MainCloverMMO.getDbHelper().getDefaultProfileByUUID(String.valueOf(player.getUniqueId()));
            if(hm.isEmpty()) {
                p = MainCloverMMO.getDbHelper().addNewProfile(player, null);
            } else {
                p = new ProfileModel(hm);
            }

            allPlayers.put(player.getName(), p);
        }
    }

    /**
     * Get the player model by playername
     *
     * @param playername    String      Get the right player by their name.
     * @return
     */
    public ProfileModel getPlayer(String playername) {
        if (allPlayers.containsKey(playername)) {
            return allPlayers.get(playername);
        } else {
            return null;
        }
    }

    /**
     * Update the player when server stops/player quits
     *
     * @param player Player      The player that needs to be saved.
     */
    public void updatePlayer(Player player) {
        if (allPlayers.containsKey(player.getName())) {
            ProfileModel p = allPlayers.get(player.getName());
            MainCloverMMO.getDbHelper().updatePlayerProfile(p);
            allPlayers.remove(player.getName());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int getPrivacy() {
        return privacy;
    }

    public void setPrivacy(int privacy) {
        this.privacy = privacy;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getRace_id() {
        return race_id;
    }

    public void setRace_id(int race_id) {
        this.race_id = race_id;
    }

    public int getFaction_id() {
        return faction_id;
    }

    public void setFaction_id(int faction_id) {
        this.faction_id = faction_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getSub_class_id() {
        return sub_class_id;
    }

    public void setSub_class_id(int sub_class_id) {
        this.sub_class_id = sub_class_id;
    }

    public int getStats_id() {
        return stats_id;
    }

    public void setStats_id(int stats_id) {
        this.stats_id = stats_id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
}
