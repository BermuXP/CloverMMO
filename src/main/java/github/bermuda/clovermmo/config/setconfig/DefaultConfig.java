package github.bermuda.clovermmo.config.setconfig;

import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;

import java.util.Arrays;
import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class DefaultConfig {

    private Database db;

    public DefaultConfig() {

        List<String> message = Arrays.asList(
                "&a&lProfile %displayname%",
                "&a&m+-------------------------------+",
                "» &6Player: &f %displayname%",
                "» &6Faction: &f %faction%",
                "» &6Race: &f %race%",
                "» &6Level: &f %level%",
                "» &6Class: &f %class%",
                "» &6Spec: &f %spec%",
                "» &6Strength: &f %strength%",
                "» &6Dexterity: &f %dexterity%",
                "» &6Constitution: &f %constitution%",
                "» &6Wisdom: &f %wisdom%",
                "» &6Intelligence: &f %intelligence%",
                "» &6Charisma: &f %charisma%",
                "» &6Luck: &f %luck%",
                "» &6EXP: &f %experience%",
                "» &6Points: &f %points%",
                "&a&m+-------------------------------+");

        clover.getConfig().addDefault("profile.message", message);

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

        clover.getConfig().addDefault("profile.GuiOrChat", "gui");

        clover.getConfig().addDefault("onjoin.OnFirstJoinMessageEnable", true);
        clover.getConfig().addDefault("onjoin.OnReturningJoinMessageEnable", true);
        clover.getConfig().addDefault("onjoin.AddPointsOnRaceSelect", 6);

        clover.getConfig().options().copyDefaults(true);
        clover.saveConfig();

    }
}
