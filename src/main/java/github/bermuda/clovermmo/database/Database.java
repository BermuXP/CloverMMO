package github.bermuda.clovermmo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import github.bermuda.clovermmo.database.model.PlayerData;
import github.bermuda.clovermmo.database.model.RacesModel;
import github.bermuda.clovermmo.database.model.UserModel;
import github.bermuda.clovermmo.database.errors.Error;
import github.bermuda.clovermmo.database.errors.Errors;
import org.bukkit.entity.Player;

import github.bermuda.clovermmo.CloverMMO;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.rm;
import static org.bukkit.Bukkit.getLogger;

public abstract class Database {
    Logger logger = getLogger();
    CloverMMO plugin;
    Connection connection;

    public Database(CloverMMO instance) {
        plugin = instance;
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize() {
        connection = getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM useraccount");
            ResultSet rs = ps.executeQuery();
            close(ps, rs);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Unable to retrieve connection", ex);
        }
    }

    public String getRace(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(String.valueOf(player.getUniqueId()))) { // Tell database to search for the player you sent into the method. e.g getTokens(sam) It will look for sam.
                    return rs.getString("race"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return "No race selected";
    }


    public String getClasses(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(String.valueOf(player.getUniqueId()))) { // Tell database to search for the player you sent into the method. e.g getTokens(sam) It will look for sam.
                    return rs.getString("pclass"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return "no class selected";
    }


    public void setRace(Player player, String race) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET race = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (race,player,strength,constitution,wisdom,charisma,intelligence,dexterity,luck,point) VALUES (?,?,?,?,?,?,?,?,?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
                int s = clover.getConfig().getInt("characteristics.strength");
                int c = clover.getConfig().getInt("characteristics.constitution");
                int w = clover.getConfig().getInt("characteristics.wisdom");
                int ch = clover.getConfig().getInt("characteristics.charisma");
                int i = clover.getConfig().getInt("characteristics.intelligence");
                int d = clover.getConfig().getInt("characteristics.dexterity");
                int l = clover.getConfig().getInt("characteristics.luck");
                int p = clover.getConfig().getInt("onjoin.AddPointsOnRaceSelect");
                ps.setInt(3, s);
                ps.setInt(4, c);
                ps.setInt(5, w);
                ps.setInt(6, ch);
                ps.setInt(7, i);
                ps.setInt(8, d);
                ps.setInt(9, l);
                ps.setInt(10, p);
            }
            ps.setString(1, race);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void setClass(Player player, String classes) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET pclass = ? WHERE player = ?");

            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (pclass,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, classes);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void setSpec(Player player, String spec) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET spec = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (spec,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, spec);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void setDatabaseClasses(String mclass) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_classes WHERE mclass = ?");
            ps.setString(1, mclass);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE table_classes SET mclass = ? WHERE _id = ?");
                ps.setInt(2, rs.getInt("_id"));
            } else {
                ps = conn.prepareStatement("INSERT INTO table_classes (mclass) VALUES (?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, mclass);   // YOU MUST put these into this line!! And depending on how man
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
        return;
    }

    public List<String> getDatabaseClasses() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> strin = new ArrayList<>();
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_classes");
            rs = ps.executeQuery();
            while (rs.next()) {
                strin.add(rs.getString("mclass"));
            }
            return strin;
        } catch (SQLException ex) {
            logger.log(Level.INFO, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return strin;
    }


    public void setDatabaseRaces(String mrace) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_races WHERE mrace = ?");

            ps.setString(1, mrace);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE table_races SET mrace = ? WHERE _id = ?");
                ps.setInt(2, rs.getInt("_id"));
            } else {
                ps = conn.prepareStatement("INSERT INTO table_races (mrace) VALUES (?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, mrace);   // YOU MUST put these into this line!! And depending on how man
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void getDatabaseRaces() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_races");
            rs = ps.executeQuery();
            while (rs.next()) {
                List<Object> arg = new ArrayList<>();
                RacesModel Races = new RacesModel();

                Races.race(
                        rs.getInt("_id"),
                        rs.getString("mrace"),
                        rs.getInt("point"),
                        rs.getInt("strength"),
                        rs.getInt("dexterity"),
                        rs.getInt("constitution"),
                        rs.getInt("wisdom"),
                        rs.getInt("charisma"),
                        rs.getInt("intelligence"),
                        rs.getInt("luck"));

                PlayerData.addGlobalModal("Race", Races);
                PlayerData.setOtherSetterData("Race", "RacesModel", arg);
            }

        } catch (SQLException ex) {
            logger.log(Level.INFO, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
    }

    public void setFaction(Player player, String faction) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET faction = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (faction,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, faction);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void setExp(Player player, int exp) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET exp = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (exp,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setInt(1, exp);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void onRacePickDB(Player p) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(p.getUniqueId()));
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(String.valueOf(p.getUniqueId()))) {
                    List<String> arg = new ArrayList<>();
                    arg.add(rs.getString("playername"));
                    arg.add(rs.getString("race"));
                    arg.add(rs.getString("point"));
                    arg.add(rs.getString("strength"));
                    arg.add(rs.getString("dexterity"));
                    arg.add(rs.getString("constitution"));
                    arg.add(rs.getString("wisdom"));
                    arg.add(rs.getString("charisma"));
                    arg.add(rs.getString("intelligence"));
                    arg.add(rs.getString("luck"));
                    PlayerData.setSetterData(p, "onRacePick", "UserModel", arg);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
    }

    public void getUserData(Player player, UserModel cc) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(String.valueOf(player.getUniqueId()))) {
                    //todo fix
                    List<String> arg = new ArrayList<>();
                    arg.add(rs.getString("playername"));
                    arg.add(rs.getString("race"));
                    arg.add(rs.getString("pclass"));
                    arg.add(rs.getString("spec"));
                    arg.add(rs.getString("faction"));
                    arg.add(rs.getString("point"));
                    arg.add(rs.getString("strength"));
                    arg.add(rs.getString("dexterity"));
                    arg.add(rs.getString("constitution"));
                    arg.add(rs.getString("wisdom"));
                    arg.add(rs.getString("charisma"));
                    arg.add(rs.getString("intelligence"));
                    arg.add(rs.getString("luck"));
                    arg.add(rs.getString("lvl"));
                    arg.add(rs.getString("exp"));

                    PlayerData.setSetterData(player, "initialize", "UserModel", arg);

//                    cc.setPlayername(rs.getString("playername"));
//                    cc.setRace(rs.getString("race"));
//                    cc.setPclass(rs.getString("pclass"));
//                    cc.setSpec(rs.getString("spec"));
//                    cc.setFaction(rs.getString("faction"));
//                    cc.setPoint(rs.getInt("point"));
//                    cc.setStrength(rs.getInt("strength"));
//                    cc.setDexterity(rs.getInt("dexterity"));
//                    cc.setConstitution(rs.getInt("constitution"));
//                    cc.setWisdom(rs.getInt("wisdom"));
//                    cc.setCharisma(rs.getInt("charisma"));
//                    cc.setIntelligence(rs.getInt("intelligence"));
//                    cc.setLuck(rs.getInt("luck"));
//                    cc.setLevel(rs.getInt("lvl"));
//                    cc.setExp(rs.getInt("exp"));
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
    }

    public void LevelUp(Player player, int level, int point) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT lvl, point FROM useraccount WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET lvl = ?, point = ? WHERE player = ?");

            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (lvl,point,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setInt(1, level);
            ps.setInt(2, point);
            ps.setString(3, String.valueOf(player.getUniqueId()));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void setUserCharacteristics(Player player, int strength, int constitution, int wisdom, int charisma, int intelligence, int dexterity, int luck) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM useraccount  WHERE player = ?");
            ps.setString(1, String.valueOf(player.getUniqueId()));
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE useraccount SET strength = ?, constitution = ?, wisdom = ?, charisma = ?, intelligence = ?, dexterity = ?, luck = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO useraccount (strength, constitution, wisdom, charisma, intelligence, dexterity, luck, player) VALUES (?,?,?,?,?,?,?,?)");
            }
            ps.setInt(1, strength);
            ps.setInt(2, constitution);
            ps.setInt(3, wisdom);
            ps.setInt(4, charisma);
            ps.setInt(5, intelligence);
            ps.setInt(6, dexterity);
            ps.setInt(7, luck);
            ps.setString(8, rs.getString("player"));
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public void setDatabaseFactions(String factions) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_factions WHERE mfaction = ?");
            ps.setString(1, factions);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE table_factions SET mfaction = ? WHERE _id = ?");
                ps.setInt(2, rs.getInt("_id"));
            } else {
                ps = conn.prepareStatement("INSERT INTO table_factions (mfaction) VALUES (?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, factions);   // YOU MUST put these into this line!! And depending on how man
            ps.executeUpdate();
            return;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
        } finally {
            close(ps, rs);
        }
        return;
    }

    public List<String> getDatabaseFactions() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> strin = new ArrayList<>();
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_factions");
            rs = ps.executeQuery();
            while (rs.next()) {
                strin.add(rs.getString("mfaction"));
            }
            return strin;
        } catch (SQLException ex) {
            logger.log(Level.INFO, Errors.sqlConnectionExecute(), ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, Errors.sqlConnectionClose(), ex);
            }
        }
        return strin;
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Error.close(plugin, ex);
        }
    }
}
