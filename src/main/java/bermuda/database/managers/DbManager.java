package bermuda.database.managers;

import bermuda.MainCloverMMO;
import bermuda.types.DatabaseTypes;

public class DbManager {

    public static DbHelper newDbManager(String type) {
        switch (type) {
            case DatabaseTypes.sqlite:
                return new SqlManager();
            case DatabaseTypes.flatFile:
                return new FlatFileManager();
            default:
                MainCloverMMO.getPlugin().getLogger().warning("Config defined Database version not found, using default Sqlite instead.");
                return new SqlManager();
        }
    }

}
