package github.bermuda.clovermmo.config;

import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;

import java.util.Arrays;
import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class DefaultConfig {

    private Database db;

    public DefaultConfig() {

        List<String> message = Arrays.asList("&a&lProfile %displayname%",
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

        List<String> races = Arrays.asList("Furfag", "Hooman", "Dwurf");

        for (String r : races) {
            clover.getConfig().addDefault("races." + r + ".maxhp", "");
            clover.getConfig().addDefault("races." + r + ".hpregen", "");
            clover.getConfig().addDefault("races." + r + ".armor", "");
            clover.getConfig().addDefault("races." + r + ".armorpenetration", "");
            clover.getConfig().addDefault("races." + r + ".handdamage", "");
            clover.getConfig().addDefault("races." + r + ".bowdamage", "");
            clover.getConfig().addDefault("races." + r + ".sworddamage", "");
            clover.getConfig().addDefault("races." + r + ".axedamage", "");
//            DefaultConfig.addDefault("races", races);
            this.db = new SQLite(clover);
            this.db.load();
            db.setDatabaseRaces(r);
        }

        List<String> classes = Arrays.asList("Paladin", "Druid", "Priest", "Hunter", "Mage", "Necromancer", "Warrior", "Thief");

        for (String s : classes) {
            List<String> randomnames = Arrays.asList("wowaspecname", "wowasecondspecname", "evenathirdspecname");
            clover.getConfig().addDefault("classes." + s + ".spec.name", randomnames);
            clover.getConfig().addDefault("classes." + s + ".maxhp", 20);
            clover.getConfig().addDefault("classes." + s + ".hpregen", "");
            clover.getConfig().addDefault("classes." + s + ".armor", "");
            clover.getConfig().addDefault("classes." + s + ".armorpenetration", "");
            clover.getConfig().addDefault("classes." + s + ".handdamage", "");
            clover.getConfig().addDefault("classes." + s + ".bowdamage", "");
            clover.getConfig().addDefault("classes." + s + ".sworddamage", "");
            clover.getConfig().addDefault("classes." + s + ".axedamage", "");
            clover.getConfig().addDefault("classes." + s + ".spec.maxhp", "");
            clover.getConfig().addDefault("classes." + s + ".spec.hpregen", "");
            clover.getConfig().addDefault("classes." + s + ".spec.armor", "");
            clover.getConfig().addDefault("classes." + s + ".spec.armorpenetration", "");
            clover.getConfig().addDefault("classes." + s + ".spec.handdamage", "");
            clover.getConfig().addDefault("classes." + s + ".spec.bowdamage", "");
            clover.getConfig().addDefault("classes." + s + ".spec.sworddamage", "");
            clover.getConfig().addDefault("classes." + s + ".spec.axedamage", "");

            this.db = new SQLite(clover);
            this.db.load();
            db.setDatabaseClasses(s);
        }

        clover.getConfig().addDefault("profile.race", true);
        clover.getConfig().addDefault("profile.classes", true);
        clover.getConfig().addDefault("profile.rankandusername", true);
        clover.getConfig().addDefault("profile.seperateusername", false);
        clover.getConfig().addDefault("profile.seperaterank", false);
        clover.getConfig().addDefault("profile.level", true);
        clover.getConfig().addDefault("profile.exp", true);
        clover.getConfig().addDefault("profile.maxhp", true);
        clover.getConfig().addDefault("profile.spec", true);
        clover.getConfig().addDefault("profile.point", true);
        clover.getConfig().addDefault("profile.charactaristics.strength", true);
        clover.getConfig().addDefault("profile.charactaristics.dexterity", true);
        clover.getConfig().addDefault("profile.charactaristics.constitution", true);
        clover.getConfig().addDefault("profile.charactaristics.intelligence", true);
        clover.getConfig().addDefault("profile.charactaristics.wisdom", true);
        clover.getConfig().addDefault("profile.charactaristics.luck", true);
        clover.getConfig().addDefault("profile.charactaristics.charisma", true);
        clover.getConfig().addDefault("profile.charactaristics.factions", true);
        clover.getConfig().addDefault("profile.charactaristics.enable", true);

        clover.getConfig().addDefault("characteristics.strenght", 1);
        clover.getConfig().addDefault("characteristics.dexterity", 1);
        clover.getConfig().addDefault("characteristics.constitution", 1);
        clover.getConfig().addDefault("characteristics.intelligence", 1);
        clover.getConfig().addDefault("characteristics.wisdom", 1);
        clover.getConfig().addDefault("characteristics.charisma", 1);
        clover.getConfig().addDefault("characteristics.luck", 1);

        clover.getConfig().addDefault("Onjoin.OnFirstJoinMessageEnable", true);
        clover.getConfig().addDefault("Onjoin.OnReturningJoinMessageEnable", true);
        clover.getConfig().addDefault("Onjoin.AddPointsOnJoin", 6);
        clover.getConfig().options().copyDefaults(true);

        clover.saveConfig();

    }
}
