package bermuda.database.managers;

import bermuda.database.models.*;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class FlatFileManager implements DbHelper {

    public FlatFileManager() {

    }

    @Override
    public void createTables() {

    }

    @Override
    public boolean profileExists(String uuid) {
        return false;
    }

    @Override
    public ProfileModel getSpecficProfile(String s) {
        return null;
    }

    @Override
    public void getPlayerProfile() {

    }

    @Override
    public void setAllConfigRaces(Set<String> races, HashMap statItem) {

    }

    @Override
    public void setAllConfigClasses(Set<String> classes, HashMap<String, Integer> statItem) {

    }

    @Override
    public HashMap<Integer, StatsModel> getAllStats(String type) {
        return null;
    }

    @Override
    public HashMap<String, Integer> setAllConfigStatsByType(Set<String> items, int type) {
        return null;
    }

    @Override
    public HashMap<String, String> getDefaultProfileByUUID(String uuid) {
        return null;
    }

    @Override
    public void updatePlayerProfile(ProfileModel pm) {}

    @Override
    public ProfileModel addNewProfile(Player player, String username) {
        return null;
    }

    @Override
    public HashMap<String, ClassModel> getAllClasses() {
        return null;
    }

    @Override
    public HashMap<String, FactionModel> getAllFactions() {
        return null;
    }

    @Override
    public HashMap<String, RacesModel> getAllRaces() {
        return null;
    }

    @Override
    public HashMap<String, String> getDataForApi() { return null; }

    @Override
    public ArrayList<SubclassModel> getSpecificSubClasses(int classId) {
        return null;
    }
}
