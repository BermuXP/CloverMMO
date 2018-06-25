package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.CloverMMO;
import github.bermuda.clovermmo.abilities.ClassAbilities;
import github.bermuda.clovermmo.database.Database;
import github.bermuda.clovermmo.database.SQLite;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static org.bukkit.Bukkit.getServer;

public class ProfileCommand implements CommandExecutor {
    private Database db;
    private ClassAbilities ability;
    public static Chat chat = null;

    public ProfileCommand(CloverMMO cmmo) {
        ability = new ClassAbilities(cmmo) {
            @Override
            public void hp(Player player) {
                super.hp(player);
            }
        };
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        this.db = new SQLite(clover);
        this.db.load();
        setupChat();
        String pf = "profile.";
        Boolean races = clover.getConfig().getBoolean(pf + "race");
        Boolean classes = clover.getConfig().getBoolean(pf + "classes");
        Boolean rank = clover.getConfig().getBoolean(pf + "seperaterank");
        Boolean username = clover.getConfig().getBoolean(pf + "seperateusername");
        Boolean usernamerank = clover.getConfig().getBoolean(pf + "rankandusername");
        Boolean level = clover.getConfig().getBoolean(pf + "level");
        Boolean exp = clover.getConfig().getBoolean(pf + "exp");
        Boolean maxhp = clover.getConfig().getBoolean(pf + "maxhp");
        Boolean spec = clover.getConfig().getBoolean(pf + "spec");

        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Profile");
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "+-------------------------------+");

        if (rank == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Rank: " + chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§"));
        }
        if (username == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Name: " + player.getDisplayName());
        }
        if (usernamerank == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Player: " + chat.getGroupPrefix(player.getWorld(), chat.getPrimaryGroup(player.getName(), player.getPlayer())).replace("&", "§") + " " + player.getDisplayName());
        }
        if (races == true) {
            String race = this.db.getRace(player);
            if (race != null) {
                sender.sendMessage("» " + ChatColor.GOLD + "Race: " + ChatColor.WHITE + race);
            } else {
                sender.sendMessage("» " + ChatColor.GOLD + "Race: " + ChatColor.WHITE + "No race selected");
            }
        }
        if (classes == true) {
            String clas = this.db.getClasses(player);
            if (clas != null) {
                sender.sendMessage("» " + ChatColor.GOLD + "Class: " + ChatColor.WHITE + clas);
            } else {
                sender.sendMessage("» " + ChatColor.GOLD + "Class: " + ChatColor.WHITE + "No class selected");
            }
        }
        if (spec == true) {
            String specs = this.db.getSpec(player);
            if (specs != null) {
                sender.sendMessage("» " + ChatColor.GOLD + "Spec: " + ChatColor.WHITE + specs);
            } else {
                sender.sendMessage("» " + ChatColor.GOLD + "Spec: " + ChatColor.WHITE + "No spec selected");
            }
        }
        if (level == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Level: " + ChatColor.WHITE + player.getLevel());
        }
        if (exp == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Exp: " + ChatColor.WHITE + player.getExp() + "/" + player.getExpToLevel());
        }
        if (maxhp == true) {
            sender.sendMessage("» " + ChatColor.GOLD + "Max Health: " + ChatColor.WHITE + String.valueOf(player.getHealthScale()));
        }
        sender.sendMessage(ChatColor.GREEN + "" + ChatColor.STRIKETHROUGH + "+-------------------------------+");
//        checkclass(player);
        return false;
    }

    public void checkclass(Player player) {
        db = new SQLite(clover);
        db.load();
        List<String> clas = clover.getConfig().getStringList("classes");

        for (String s : clas) {
            String classes = this.db.getClasses(player);
            if (s.equalsIgnoreCase(classes)) {
                this.ability.hp(player);
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
