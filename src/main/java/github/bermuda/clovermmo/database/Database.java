package github.bermuda.clovermmo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import github.bermuda.clovermmo.database.errors.Error;
import github.bermuda.clovermmo.database.errors.Errors;
import org.bukkit.entity.Player;

import github.bermuda.clovermmo.CloverMMO;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getLogger;

public abstract class Database {
    Logger logger = getLogger();
    CloverMMO plugin;
    Connection connection;
    // The name of the table we created back in SQLite class.
    public String table = "CloverDB";

    public Database(CloverMMO instance) {
        plugin = instance;
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize() {
        connection = getSQLConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + table);
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
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(player.getName().toLowerCase())) { // Tell database to search for the player you sent into the method. e.g getTokens(sam) It will look for sam.
                    return rs.getString("race"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
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
        return "No race selected";
    }

    public String getSpec(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(player.getName().toLowerCase())) { // Tell database to search for the player you sent into the method. e.g getTokens(sam) It will look for sam.
                    return rs.getString("spec"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
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
        return "No spec selected";
    }

    public String getClasses(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(player.getName().toLowerCase())) { // Tell database to search for the player you sent into the method. e.g getTokens(sam) It will look for sam.
                    return rs.getString("pclass"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
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
        return "no class selected";
    }


    public void setRace(Player player, String race) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE " + table + " SET race = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO " + table + " (race,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, race);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, player.getName().toLowerCase());
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

    public void setClass(Player player, String classes) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE " + table + " SET pclass = ? WHERE player = ?");

            } else {
                ps = conn.prepareStatement("INSERT INTO " + table + " (pclass,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, classes);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, player.getName().toLowerCase());
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

    public void setSpec(Player player, String spec) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                ps = conn.prepareStatement("UPDATE " + table + " SET spec = ? WHERE player = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO " + table + " (spec,player) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
            }
            ps.setString(1, spec);   // YOU MUST put these into this line!! And depending on how man
            ps.setString(2, player.getName().toLowerCase());
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
        List<String> strin = new ArrayList<String>();
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

    public void addpoints(int point, String player) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.toLowerCase());
            rs = ps.executeQuery();
            if (rs.next()) {
                plugin.getLogger().log(Level.WARNING, "eehm you're not supposed to see this message something went wrong contact the plugin developer");
            } else {
                ps = conn.prepareStatement("INSERT INTO " + table + " (point,player) VALUES (?,?)");
            }
            ps.setInt(1, point);
            ps.setString(2, player.toLowerCase());
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

    public int getpoints(Player player) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM " + table + " WHERE player = ?");
            ps.setString(1, player.getName().toLowerCase());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("player").equalsIgnoreCase(player.getName().toLowerCase())) { // Tell database to search for the player you sent into the method. e.g getTokens(sam) It will look for sam.
                    return rs.getInt("point"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, Errors.sqlConnectionExecute(), ex);
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
        return 0;
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

    public List<String> getDatabaseRaces() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> strin = new ArrayList<String>();
        try {
            conn = getSQLConnection();
            ps = conn.prepareStatement("SELECT * FROM table_races");
            rs = ps.executeQuery();
            while (rs.next()) {
                strin.add(rs.getString("mrace"));
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
