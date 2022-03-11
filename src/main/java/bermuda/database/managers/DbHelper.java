package bermuda.database.managers;


import bermuda.database.models.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public interface DbHelper {

    public void createTables();

    public boolean profileExists(String uuid);

    public ProfileModel getSpecficProfile(String s);

    public void getPlayerProfile();

    public void setAllConfigClasses(Set<String> classes, HashMap<String, Integer> statItem);

    public void setAllConfigRaces(Set<String> races, HashMap statItem);

    public HashMap<Integer, StatsModel> getAllStats(String type);

    public HashMap<String, Integer> setAllConfigStatsByType(Set<String> items, int type);

    public HashMap<String, String> getDefaultProfileByUUID(String uuid);

    public void updatePlayerProfile(ProfileModel pm);

    public ProfileModel addNewProfile(Player player, String username);

    public HashMap<String, ClassModel> getAllClasses();

    public HashMap<String, FactionModel> getAllFactions();

    public HashMap<String, RacesModel> getAllRaces();

    public HashMap<String, String> getDataForApi();

    public ArrayList<SubclassModel> getSpecificSubClasses(int classId);

}
