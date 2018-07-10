package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.abilities.ClassAbilities;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import github.bermuda.clovermmo.database.UseraccountDB;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.phapi;
import static org.bukkit.Bukkit.getServer;

public class ProfileCMD implements CommandExecutor {
    private Database db;
    private ClassAbilities ability;

    public static Chat chat = null;

    public ProfileCMD(CloverMMO cmmo) {
        ability = new ClassAbilities(cmmo) {};
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        this.db = new SQLite(clover);
        this.db.load();
        db.getUserData(player);
        setupChat();
        String pf = "profile.";
        Boolean races = clover.getConfig().getBoolean(pf + "race");
        Boolean classes = clover.getConfig().getBoolean(pf + "classes");
        Boolean rank = clover.getConfig().getBoolean(pf + "seperaterank");
        Boolean username = clover.getConfig().getBoolean(pf + "seperateusername");
        Boolean usernamerank = clover.getConfig().getBoolean(pf + "rankandusername");
        Boolean level = clover.getConfig().getBoolean(pf + "level");
        Boolean exp = clover.getConfig().getBoolean(pf + "exp");
        Boolean spec = clover.getConfig().getBoolean(pf + "spec");
        Boolean points = clover.getConfig().getBoolean(pf + "point");

        Boolean charactaristics = clover.getConfig().getBoolean(pf + "charactaristics.enable");
        Boolean strength = clover.getConfig().getBoolean(pf + "charactaristics.strength");
        Boolean dexterity = clover.getConfig().getBoolean(pf + "charactaristics.dexterity");
        Boolean constitution = clover.getConfig().getBoolean(pf + "charactaristics.constitution");
        Boolean intelligence = clover.getConfig().getBoolean(pf + "charactaristics.intelligence");
        Boolean wisdom = clover.getConfig().getBoolean(pf + "charactaristics.wisdom");
        Boolean charisma = clover.getConfig().getBoolean(pf + "charactaristics.charisma");
        Boolean luck = clover.getConfig().getBoolean(pf + "charactaristics.luck");
        Boolean faction = clover.getConfig().getBoolean(pf + "charactaristics.factions");

        String text = clover.getConfig().getString("profile.message");
        sender.sendMessage(text);

        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Profile");
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "+-------------------------------+");

        if (rank == true) {
            player.sendMessage("» " + ChatColor.GOLD + "Rank: " + chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§"));
        }
        if (username == true) {
            player.sendMessage("» " + ChatColor.GOLD + "Name: " + player.getDisplayName());
        }
        if (usernamerank == true) {
            //chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§") + " " + player.getDisplayName()
            player.sendMessage("» " + ChatColor.GOLD + "Player: " + phapi.onPlaceholderRequest(player, "displayname"));
        }
        if (level == true) {
            player.sendMessage("» " + ChatColor.GOLD + "Level: " + ChatColor.WHITE + player.getLevel());
        }
        if (faction == true) {
            String factions = cc.getFaction();
            if (factions != null) {
                player.sendMessage("» " + ChatColor.GOLD + "Faction: " + ChatColor.WHITE + factions);
            } else {
                player.sendMessage("» " + ChatColor.GOLD + "Faction: " + ChatColor.WHITE + "No faction selected");
            }
        }
        if (races == true) {
//            String race = this.db.getRace(player);
            String race = cc.getRace();
            if (race != null) {
                String wowie = clover.getConfig().getString("test");
                player.sendMessage(wowie);
                player.sendMessage("» " + ChatColor.GOLD + "Race: " + ChatColor.WHITE + race);
            } else {
                player.sendMessage("» " + ChatColor.GOLD + "Race: " + ChatColor.WHITE + "No race selected");
            }
        }
        if (classes == true) {
//            String mclass = this.db.getClasses(player);
            String mclass = cc.getPclass();
            if (mclass != null) {
                player.sendMessage("» " + ChatColor.GOLD + "Class: " + ChatColor.WHITE + mclass);
            } else {
                player.sendMessage("» " + ChatColor.GOLD + "Class: " + ChatColor.WHITE + "No class selected");
            }
        }
        if (charactaristics == true) {
            if (strength == true) {
                int stg = cc.getStrength();
                player.sendMessage("» " + ChatColor.GOLD + "Strength: " + ChatColor.WHITE + stg);
            }

            if (dexterity == true) {
                int dex = cc.getDexterity();
                player.sendMessage("» " + ChatColor.GOLD + "Dexterity: " + ChatColor.WHITE + dex);
            }

            if (constitution == true) {
                int con = cc.getConstitution();
                player.sendMessage("» " + ChatColor.GOLD + "Constitution: " + ChatColor.WHITE + con);
            }

            if (wisdom == true) {
                int wis = cc.getWisdom();
                player.sendMessage("» " + ChatColor.GOLD + "Wisdom: " + ChatColor.WHITE + wis);
            }

            if (intelligence == true) {
                int intell = cc.getIntelligence();
                player.sendMessage("» " + ChatColor.GOLD + "Intelligence: " + ChatColor.WHITE + intell);
            }

            if (charisma == true) {
                int cha = cc.getCharisma();
                player.sendMessage("» " + ChatColor.GOLD + "Charisma: " + ChatColor.WHITE + cha);
            }

            if (luck == true) {
                int lu = cc.getLuck();
                player.sendMessage("» " + ChatColor.GOLD + "Luck: " + ChatColor.WHITE + lu);
            }
        }

        if (spec == true) {
//            String specs = this.db.getSpec(player);
            String specs = cc.getSpec();
            if (specs != null) {
                player.sendMessage("» " + ChatColor.GOLD + "Spec: " + ChatColor.WHITE + specs);
            } else {
                player.sendMessage("» " + ChatColor.GOLD + "Spec: " + ChatColor.WHITE + "No spec selected");
            }
        }

        if (exp == true) {
            player.sendMessage("» " + ChatColor.GOLD + "Exp: " + ChatColor.WHITE + player.getExp() + "/" + player.getExpToLevel());
        }
//        if (maxhp == true) {
//            sender.sendMessage("» " + ChatColor.GOLD + "Max Health: " + ChatColor.WHITE + String.valueOf(player.getHealthScale()));
//        }
        if (points == true) {
//            int p = this.db.getpoints(player);
            int p = cc.getPoint();
            player.sendMessage("» " + ChatColor.GOLD + "Points: " + ChatColor.WHITE + p);
        }
//        checkclass(player);
        player.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "+-------------------------------+");
        return false;
    }

    @EventHandler
    public void test(EntityDamageByEntityEvent event) {
        this.ability.damageEvent(event);
    }

    public void checkclass(Player player) {
        db = new SQLite(clover);
        db.load();
//        List<String> clas = clover.getConfig().getStringList("classes");
        List<String> clas = this.db.getDatabaseClasses();

        for (String s : clas) {
            String classes = this.db.getClasses(player);
            if (s.equalsIgnoreCase(classes)) {
                int classmhp = clover.getConfig().getInt("classes." + classes + ".maxhp");
                int racemhp = clover.getConfig().getInt("classes." + classes + ".maxhp");
                int specmhp = clover.getConfig().getInt("classes." + classes + ".maxhp");

                int calcmhp = classmhp + racemhp + specmhp;

                this.ability.hp(player, calcmhp);
            }
        }
        if (clas.isEmpty()) {
            player.sendMessage(clover.cloverprefix + "You need to select a class before you can do this /class [classname] or /class to see all the possible classe");
        }
    }

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    public static Chat getChat() {
        return chat;
    }

}
