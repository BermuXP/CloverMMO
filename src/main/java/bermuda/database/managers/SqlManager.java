package bermuda.database.managers;

import bermuda.MainCloverMMO;
import bermuda.config.ClassConfig;
import bermuda.config.DefaultConfig;
import bermuda.config.RaceConfig;
import bermuda.database.models.*;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

import static bermuda.MainCloverMMO.getPlugin;
import static bermuda.database.Database.*;
import static bermuda.types.StatTypes.subclass_type;

public class SqlManager implements DbHelper {
    private Connection connection;

    /**
     * constructor
     */
    public SqlManager() {
        // empty construct
    }

    /**
     * set all the databases
     */
    public void createTables() {
        connection = getSQLConnection();
        if (connection != null) {
            Statement s = null;
            try {
                s = connection.createStatement();
                s.addBatch(CreateRacesTable);
                s.addBatch(CreateClassesTable);
                s.addBatch(CreateFactionsTable);
                s.addBatch(CreateUsersTable);
                s.addBatch(CreateProfileTable);
                s.addBatch(CreateStatsTable);
                s.addBatch(CreateSubClassesTable);
                s.addBatch(CreateAchievementsTable);
                s.addBatch(CreateProfessionsTable);
                s.addBatch(CreateProfessionsLinkTable);
                s.addBatch(CreateAchievementsLinkTable);
                s.addBatch(createNPCTable);
                s.executeBatch();
                s.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * check if profile exists
     * @param uuid      user UUID
     * @return
     */
    public boolean profileExists(String uuid) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean data = false;
        try {
            Connection conn = getSQLConnection();
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement("SELECT * FROM `" + tableProfile + "` WHERE uuid = ?");

            pstmt.setString(1, uuid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                data = true;
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return data;
    }

    /**
     * get Sql connection/create DB.
     * @return
     */
    private Connection getSQLConnection() {
        File dataFolder = new File(getPlugin().getDataFolder(), "CloverDB.db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            getPlugin().getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            getPlugin().getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    @Override
    public void getPlayerProfile() {

    }

    /**
     * Get specific sub class.
     * @param classId           class id.
     * @return                  Sub class model return.
     */
    public ArrayList<SubclassModel> getSpecificSubClasses(int classId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SubclassModel> arrayList = new ArrayList<>();
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `" + tableSubClasses + "` WHERE class_id = " + classId);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                HashMap<String, String> hashList = new HashMap<>();
                for (int i = 1; i <= columnCount; i++ ) {
                    String name = rsmd.getColumnName(i);
                    hashList.put(name, rs.getString(name));
                }
                arrayList.add(new SubclassModel(hashList));
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return arrayList;
    }

    /**
     * Set all race config data.
     * @param classes
     * @param statItem
     */
    public void setAllConfigClasses(Set<String> classes, HashMap<String, Integer> statItem) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO `" + tableClasses + "` (`name`, `stats_id`, `description`) values (?,?,?)");
            for (String className : classes) {
                Set<String> subClasses = ClassConfig.getInstance().getSubClassNames(className);
                for (String subName : subClasses) {
                    String subClassConfigStats = "Classes." + className + ".Spec." + subName;
                    int statId = setConfigStats(subClassConfigStats, subclass_type);
                    String desc =  ClassConfig.getInstance().getSpecDescription(className, subName);
                    setConfigSubClass(subName, statId, desc);
                }
                ps.setString(1, className);
                ps.setInt(2, statItem.get(className));
                ps.setString(3, ClassConfig.getInstance().getClassDescription(className));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("SQL error:" + ex);
        } finally {
            close(ps, rs);
        }
    }

    /**
     * Set specific config stat.
     * @param configString
     * @param type
     * @return
     */
    public int setConfigStats(String configString, int type) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO `" + tableStats + "` (`strength`, `dexterity`, `constitution`, `wisdom`, `charisma`, `intelligence`, `luck`, `type`) values (?,?,?,?,?,?,?,?)");
            Statement stmt = conn.createStatement();
            ps.setInt(1, DefaultConfig.getInstance().getUnknownConfigInt(configString + "strength"));
            ps.setInt(2, DefaultConfig.getInstance().getUnknownConfigInt(configString + "dexterity"));
            ps.setInt(3, DefaultConfig.getInstance().getUnknownConfigInt(configString + "constitution"));
            ps.setInt(4, DefaultConfig.getInstance().getUnknownConfigInt(configString + "wisdom"));
            ps.setInt(5, DefaultConfig.getInstance().getUnknownConfigInt(configString + "charisma"));
            ps.setInt(6, DefaultConfig.getInstance().getUnknownConfigInt(configString + "intelligence"));
            ps.setInt(7, DefaultConfig.getInstance().getUnknownConfigInt(configString + "luck"));
            ps.setInt(8, type);
            ps.executeUpdate();
            ResultSet newid = stmt.executeQuery("SELECT last_insert_rowid() as `last_id`;");
            id = newid.getInt("last_id");

        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return id;
    }

    /**
     * Set config subclasses.
     * @param subName
     * @param statId
     * @param desc
     */
    private void setConfigSubClass(String subName, int statId, String desc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO `" + tableSubClasses + "` (`name`, `stats_id`, `description`) values (?,?,?)");
            ps.setString(1, subName);
            ps.setInt(2, statId);
            ps.setString(3, desc);
            ps.addBatch();

            ps.executeBatch();
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("SQL error:" + ex);
        } finally {
            close(ps, rs);
        }
    }

    /**
     * Set all race config data.
     * @param races
     * @param statItem
     */
    public void setAllConfigRaces(Set<String> races, HashMap statItem) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO `" + tableRaces + "` (`name`, `stats_id`, `description`) values (?,?,?)");
            for (String race : races) {
                ps.setString(1, race);
                ps.setInt(2, (Integer) statItem.get(race));
                ps.setString(3, RaceConfig.getInstance().getRaceDescription(race));
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("SQL error:" + ex);
        } finally {
            close(ps, rs);
        }
    }

    /**
     * get all the stats from the database
     */
    public HashMap<Integer, StatsModel> getAllStats(String type) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer, StatsModel> hashList = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            String where = "";
            if (type != null) {
                where = "WHERE `type` = `" + type + "`";
            }
            rs = stmt.executeQuery("SELECT * FROM `" + tableStats + "` " + where);
            while (rs.next()) {
                int id = rs.getInt("id");
                String stattype = rs.getString("type");
                int strength = rs.getInt("strength");
                int dexterity = rs.getInt("dexterity");
                int constitution = rs.getInt("constitution");
                int wisdom = rs.getInt("wisdom");
                int charisma = rs.getInt("charisma");
                int intelligence = rs.getInt("intelligence");
                int luck = rs.getInt("luck");
                hashList.put(id, new StatsModel(id, stattype, strength, dexterity, constitution, wisdom, charisma, intelligence, luck));
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return hashList;
    }

    /**
     * set all the stats from a specific race config by it's type
     */
    public HashMap<String, Integer> setAllConfigStatsByType(Set<String> items, int type) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, Integer> itemHash = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO `" + tableStats + "` (`strength`, `dexterity`, `constitution`, `wisdom`, `charisma`, `intelligence`, `luck`, `type`) values (?,?,?,?,?,?,?,?)");
            RaceConfig rc = new RaceConfig();
            Statement stmt = conn.createStatement();
            for (String item : items) {
                HashMap<String, Integer> raceStats = rc.getRaceStats(item);
                ps.setInt(1, raceStats.get("strength"));
                ps.setInt(2, raceStats.get("dexterity"));
                ps.setInt(3, raceStats.get("constitution"));
                ps.setInt(4, raceStats.get("wisdom"));
                ps.setInt(5, raceStats.get("charisma"));
                ps.setInt(6, raceStats.get("intelligence"));
                ps.setInt(7, raceStats.get("luck"));
                ps.setInt(8, type);
                ps.executeUpdate();
                ResultSet newid = stmt.executeQuery("SELECT last_insert_rowid() as `last_id`;");
                itemHash.put(item, newid.getInt("last_id"));
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return itemHash;
    }

    //===============PROFILE=================
    public HashMap<String, String> getDefaultProfileByUUID(String uuid) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, String> hashList = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM `" + tableProfile + "` WHERE `uuid` = ? AND `selected` = 1 LIMIT 1");
            ps.setString(1, String.valueOf(uuid));
            rs = ps.executeQuery();
            rs.next();

            hashList.put("id", rs.getString("id"));
            hashList.put("name", rs.getString("name"));
            hashList.put("description", rs.getString("description"));
            hashList.put("selected", rs.getString("selected"));
            hashList.put("privacy", rs.getString("privacy"));
            hashList.put("uuid", rs.getString("uuid"));
            hashList.put("race_id", rs.getString("race_id"));
            hashList.put("faction_id", rs.getString("faction_id"));
            hashList.put("class_id", rs.getString("class_id"));
            hashList.put("sub_class_id", rs.getString("sub_class_id"));
            hashList.put("stat_id", rs.getString("stats_id"));
            hashList.put("point", rs.getString("point"));
            hashList.put("exp", rs.getString("exp"));
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return hashList;
    }

    public void updatePlayerProfile(ProfileModel pm) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = getSQLConnection();
            if (conn != null) {
                ps = conn.prepareStatement("UPDATE `" + tableProfile + "` SET `description`=?,`privacy`=?,`race_id`=?,`faction_id`=?,`class_id`=?,`sub_class_id`=?,`point`=?,`exp`=? WHERE `uuid` = ? `selected` = 1");
                ps.setString(1, pm.getDescription());
                ps.setInt(2, pm.getPrivacy());
                ps.setInt(3, pm.getRace_id());
                ps.setInt(4, pm.getFaction_id());
                ps.setInt(5, pm.getClass_id());
                ps.setInt(6, pm.getSub_class_id());
                ps.setInt(7, pm.getPoint());
                ps.setInt(8, pm.getExp());
                ps.setString(9, pm.getUuid());
                ps.executeUpdate();
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
    }

    /**
     * Add a new profile.
     *
     * @param player
     * @return
     */
    public ProfileModel addNewProfile(Player player, String username) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProfileModel profileModel = null;
        if (username == null) {
            username = player.getName();
        }
        try {
            Connection conn = getSQLConnection();
            ps = conn.prepareStatement("INSERT INTO `" + tableProfile + "` (`point`, `exp`, `selected`, `privacy`, `name`, `uuid`) values (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, 1);
            ps.setInt(2, 100);
            ps.setInt(3, 1);
            ps.setInt(4, 1);
            ps.setString(5, username);
            ps.setString(6, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            Statement stmt = conn.createStatement();
            ResultSet newid = stmt.executeQuery("SELECT last_insert_rowid() as last_id;");
            //todo fix points/exp from config
            profileModel = new ProfileModel(newid.getInt("last_id"), String.valueOf(player.getUniqueId()), 1, 100);
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return profileModel;
    }

    /**
     * Get specfic profile else return null.
     * @param uuid
     * @return
     */
    public ProfileModel getSpecficProfile(String uuid) {
        ProfileModel profileModel = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `" + tableProfile + "` WHERE uuid = " + uuid + "LIMIT 1");
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                HashMap<String, String> hm = new HashMap<>();
                for (int i = 1; i <= columnCount; i++ ) {
                    String tableName = rsmd.getColumnName(i);
                    hm.put(tableName, rs.getString(i));
                }
                profileModel = new ProfileModel(hm);
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return profileModel;
    }

    //===============CLASSES===============

    /**
     * get all the classes from the DB
     *
     * @return HashMap<String, ClassModel>
     */
    public HashMap<String, ClassModel> getAllClasses() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, ClassModel> hashList = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `" + tableClasses + "`");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int stats_id = rs.getInt("stats_id");
                String description = rs.getString("description");
                hashList.put(rs.getString("name"), new ClassModel(id, name, stats_id, description));
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return hashList;
    }

    //===============SUBCLASSES===============

    //===============FACTIONS===============
    public HashMap<String, FactionModel> getAllFactions() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, FactionModel> hashList = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `" + tableFactions + "`");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                hashList.put(rs.getString("name"), new FactionModel(id, name, description));
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return hashList;
    }


    //===============RACES===============

    /**
     * get all the races from the database
     */
    public HashMap<String, RacesModel> getAllRaces() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<String, RacesModel> hashList = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `" + tableRaces + "`");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int stats_id = rs.getInt("stats_id");
                String description = rs.getString("description");
                hashList.put(rs.getString("name"), new RacesModel(id, name, stats_id, description));
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return hashList;
    }

    public void setUserRace(Player player, String race) {

    }

    /**
     *
     * @return
     */
    public HashMap<String, String> getDataForApi() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String racePrefix = "race_";
        String classPrefix = "class_";
        String factionPrefix = "faction_";
        String subClassPrefix = "subclass_";
        HashMap<String, String> hashList = new HashMap<>();
        try {
            Connection conn = getSQLConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `" + tableRaces + "`");
            ResultSetMetaData raceData = rs.getMetaData();
            int raceCount = raceData.getColumnCount();
            for (int i = 1; i <= raceCount; i++) {
                hashList.put(racePrefix + raceData.getColumnName(i), rs.getString(raceData.getColumnName(i)));
            }

            rs = stmt.executeQuery("SELECT * FROM `" + tableClasses + "`");
            ResultSetMetaData classData = rs.getMetaData();
            int classCount = classData.getColumnCount();
            for (int i = 1; i <= classCount; i++) {
                hashList.put(classPrefix + classData.getColumnName(i), rs.getString(classData.getColumnName(i)));
            }

            rs = stmt.executeQuery("SELECT * FROM `" + tableFactions + "`");
            ResultSetMetaData factionData = rs.getMetaData();
            int factionCount = classData.getColumnCount();
            for (int i = 1; i <= factionCount; i++) {
                hashList.put(factionPrefix + factionData.getColumnName(i), rs.getString(factionData.getColumnName(i)));
            }

            rs = stmt.executeQuery("SELECT * FROM `" + tableSubClasses + "`");
            ResultSetMetaData subClassData = rs.getMetaData();
            int subClassCount = classData.getColumnCount();
            for (int i = 1; i <= subClassCount; i++) {
                hashList.put(subClassPrefix + subClassData.getColumnName(i), rs.getString(subClassData.getColumnName(i)));
            }

        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("sql error " + ex);
        } finally {
            close(ps, rs);
        }
        return hashList;
    }

    /**
     * close the sqllite connection
     *
     * @param ps
     * @param rs
     */
    private void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            MainCloverMMO.getPlugin().getLogger().warning("Failed to close MySQL connection: " + ex);
        }
    }
}
