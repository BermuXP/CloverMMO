package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;

import java.util.Arrays;
import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class DefaultConfig {

    private Database db;

    public DefaultConfig() {

        List<String> factions = Arrays.asList("Alliance", "Horde");
        for (String f : factions) {
            clover.getConfig().addDefault("factions." + f, "");
//            clover.getConfig().addDefault("factions." + f + "races", "");
            this.db = new SQLite(clover);
            this.db.load();
            db.setDatabaseFactions(f);
        }

        clover.getConfig().addDefault("characteristics.strength", 1);
        clover.getConfig().addDefault("characteristics.dexterity", 1);
        clover.getConfig().addDefault("characteristics.constitution", 1);
        clover.getConfig().addDefault("characteristics.intelligence", 1);
        clover.getConfig().addDefault("characteristics.wisdom", 1);
        clover.getConfig().addDefault("characteristics.charisma", 1);
        clover.getConfig().addDefault("characteristics.luck", 1);

        clover.getConfig().addDefault("onjoin.OnFirstJoinMessageEnable", true);
        clover.getConfig().addDefault("onjoin.OnReturningJoinMessageEnable", true);
        clover.getConfig().addDefault("onjoin.AddPointsOnRaceSelect", 6);

        clover.getConfig().options().copyDefaults(true);
        clover.saveConfig();

    }
}
