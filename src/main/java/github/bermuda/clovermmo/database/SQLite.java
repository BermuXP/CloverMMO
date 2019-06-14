package github.bermuda.clovermmo.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.database.Database;

public class SQLite extends Database {
    String dbname;

    public SQLite(CloverMMO instance) {
        super(instance);
        dbname = plugin.getConfig().getString("SQLite.Filename", "CloverDB"); // Set the table name here e.g player_kills
    }

    public String SQLiteCreateTokensTable =
            "CREATE TABLE IF NOT EXISTS useraccount(" +
            "`playername` varchar(32) NULL," +
            "`player` varchar(32) NOT NULL," +
            "`race` int(11) NULL," +
            "`pclass` int(11) NULL," +
            "`spec` varchar(32) NULL," +
            "`faction` varchar(32) NULL," +
            "`point` int(11) NULL," +
            "`strength` int (11) NULL," +
            "`dexterity` int (11) NULL," +
            "`constitution` int (11) NULL," +
            "`wisdom` int (11) NULL," +
            "`charisma` int (11) NULL," +
            "`intelligence` int (11) NULL," +
            "`luck` int (11) NULL," +
            "`lvl` int (11) NULL," +
            "`exp` int (11) NULL," +
            "PRIMARY KEY (`player`));" +

            "CREATE TABLE IF NOT EXISTS table_classes(" +
            "`_id` integer PRIMARY KEY NOT NULL," +
            "`mclass` varchar(32) NOT NULL," +
            "`strength` int (11) NULL," +
            "`dexterity` int (11) NULL," +
            "`constitution` int (11) NULL," +
            "`wisdom` int (11) NULL," +
            "`charisma` int (11) NULL," +
            "`intelligence` int (11) NULL," +
            "`luck` int (11) NULL);" +

            "CREATE TABLE IF NOT EXISTS table_races(" +
            "`_id` integer PRIMARY KEY NOT NULL," +
            "`mrace` varchar(32) NOT NULL," +
            "`point` int(11) NULL," +
            "`strength` int (11) NULL," +
            "`dexterity` int (11) NULL," +
            "`constitution` int (11) NULL," +
            "`wisdom` int (11) NULL," +
            "`charisma` int (11) NULL," +
            "`intelligence` int (11) NULL," +
            "`luck` int (11) NULL);" +

            "CREATE TABLE IF NOT EXISTS table_factions(" +
            "`_id` integer PRIMARY KEY NOT NULL, " +
            "`mfaction` varchar(32) NOT NULL);";

    public Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder(), dbname + ".db");
        if (!dataFolder.exists()) {
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "File write error: " + dbname + ".db");
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
            plugin.getLogger().log(Level.SEVERE, "SQLite exception on initialize", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "You need the SQLite JBDC library. Google it. Put it in /lib folder.");
        }
        return null;
    }

    public void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(SQLiteCreateTokensTable);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }
}