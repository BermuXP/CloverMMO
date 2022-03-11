package bermuda.database;

public abstract class Database {

    public static String tableRaces = "races";
    public static String tableClasses = "classes";
    public static String tableUsers = "users";
    public static String tableStats = "stats";
    public static String tableProfile = "profiles";
    public static String tableFactions = "factions";
    public static String tableSubClasses = "subclasses";

    public static String CreateRacesTable =
            "CREATE TABLE IF NOT EXISTS races(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`name` varchar (255) NULL UNIQUE," +
                    "`description` TEXT NULL, " +
                    "`stats_id` integer (11) DEFAULT 0);";

    public static String CreateAchievementsTable =
            "CREATE TABLE IF NOT EXISTS achievements(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`name` varchar (255) NULL UNIQUE," +
                    "`description` TEXT NULL);";

    public static String CreateProfessionsTable =
            "CREATE TABLE IF NOT EXISTS achievements(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`name` varchar (255) NULL UNIQUE," +
                    "`description` TEXT NULL);";

    public static String CreateClassesTable =
            "CREATE TABLE IF NOT EXISTS classes(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`name` varchar (255) NULL UNIQUE," +
                    "`description` TEXT NULL, " +
                    "`stats_id` integer (11) DEFAULT 0);";

    public static String CreateFactionsTable =
            "CREATE TABLE IF NOT EXISTS factions(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`name` varchar (255) NULL UNIQUE," +
                    "`description` TEXT NULL);";

    public static String CreateUsersTable =
            "CREATE TABLE IF NOT EXISTS users(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`uuid` varchar (125) NULL UNIQUE);";

    public static String CreateSubClassesTable =
            "CREATE TABLE IF NOT EXISTS subclasses(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`stats_id` integer (11) DEFAULT 0, " +
                    "`class_id` integer (11) DEFAULT 0, " +
                    "`description` TEXT NULL, " +
                    "`name` varchar (125) NULL);";

    //  default = 1 for private so others can't see their profile
    public static String CreateProfileTable =
            "CREATE TABLE IF NOT EXISTS profiles(" +
                    "`id` integer PRIMARY KEY NOT NULL, " +
                    "`name` varchar (125) DEFAULT NULL, " +
                    "`description` TEXT NULL, " +
                    "`selected` TINYINT (1) DEFAULT 0, " +
                    "`privacy` TINYINT (1) DEFAULT 1, " +
                    "`uuid` text NOT NULL, " +
                    "`race_id` int (11) DEFAULT 0," +
                    "`faction_id` int (11) DEFAULT 0," +
                    "`class_id` int (11) DEFAULT 0," +
                    "`sub_class_id` int (11) DEFAULT 0," +
                    "`point` int (11) DEFAULT 0, " +
                    "`exp` int (11) DEFAULT 0);";

    public static String CreateStatsTable =
            "CREATE TABLE IF NOT EXISTS stats(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`type` int (11) DEFAULT 0, " +
                    "`strength` int (11) DEFAULT 0, " +
                    "`dexterity` int  (11) DEFAULT 0, " +
                    "`constitution` int (11) DEFAULT 0, " +
                    "`wisdom` int (11) DEFAULT 0, " +
                    "`charisma` int (11) DEFAULT 0, " +
                    "`intelligence` int (11) DEFAULT 0, " +
                    "`luck` int (11) DEFAULT 0);";

    public static String CreateProfessionsLinkTable =
            "CREATE TABLE IF NOT EXISTS profile_profession(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`uuid` text NOT NULL, " +
                    "`profession_id` integer (11) NOT NULL);";

    public static String CreateAchievementsLinkTable =
            "CREATE TABLE IF NOT EXISTS user_achievements(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`uuid` text NOT NULL, " +
                    "`achievement_id` integer (11) NOT NULL);";

    public static String createNPCTable =
            "CREATE TABLE IF NOT EXISTS npc_users(" +
                    "`id` integer PRIMARY KEY NOT NULL," +
                    "`name` text NOT NULL);";
}