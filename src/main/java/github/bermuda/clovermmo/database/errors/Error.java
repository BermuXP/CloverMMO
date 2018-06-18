package github.bermuda.clovermmo.database.errors;

import github.bermuda.clovermmo.CloverMMO;

import java.util.logging.Level;

public class Error {

    public static void execute(CloverMMO plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Couldn't execute MySQL statement: ", ex);
    }
    public static void close(CloverMMO plugin, Exception ex){
        plugin.getLogger().log(Level.SEVERE, "Failed to close MySQL connection: ", ex);
    }
}

