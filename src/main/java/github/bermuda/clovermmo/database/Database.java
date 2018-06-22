package github.bermuda.clovermmo.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import github.bermuda.clovermmo.database.errors.Error;
import github.bermuda.clovermmo.database.errors.Errors;
import org.bukkit.entity.Player;

import github.bermuda.clovermmo.CloverMMO;

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
        return "no race selected";
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
                    return rs.getString("class"); // Return the players ammount of kills. If you wanted to get total (just a random number for an example for you guys) You would change this to total!
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
                ps = conn.prepareStatement("UPDATE " + table + " SET `race` = ? WHERE `player` = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO " + table + " (`race`,`player`) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
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
                ps = conn.prepareStatement("UPDATE " + table + " SET `class` = ? WHERE `player` = ?");
            } else {
                ps = conn.prepareStatement("INSERT INTO " + table + " (`class`,`player`) VALUES (?,?)"); // IMPORTANT. In SQLite class, We made 3 colums. player, Kills, Total.
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
