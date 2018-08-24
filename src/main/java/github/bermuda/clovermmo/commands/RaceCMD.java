package github.bermuda.clovermmo.commands;

import github.bermuda.clovermmo.commands.gui.RaceGuiCMD;
import github.bermuda.clovermmo.config.setconfig.DefaultConfig;

import github.bermuda.clovermmo.config.setconfig.RaceConfig;
import github.bermuda.clovermmo.database.model.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;
import static github.bermuda.clovermmo.CloverMMO.rm;

public class RaceCMD implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(clover.cloverprefix + "you must be a player to perform this command!");
            return false;
        }
        Player p = (Player) sender;
        if (DefaultConfig.config().getGuiOrChatRace().equalsIgnoreCase("gui")) {
            RaceGuiCMD.RaceGUI(p);
            return true;
        } else {
            if (args.length == 2) {
//                List<String> races = db.getDatabaseRaces();
                List<String> races = rm.getRaceName();
                if (args[0].equalsIgnoreCase("select") || args[0].equalsIgnoreCase("sel")) {
//                    clover.getLogger().info(races.toString());
                    boolean match = false;
                    if (races == null) {
                        p.sendMessage(clover.cloverprefix + "The server owner did not add any races yet. ");
                    } else {
                        for (String s : races) {
                            if (args[1].equalsIgnoreCase(s)) {
                                p.sendMessage(clover.cloverprefix + "You have successfully selected " + ChatColor.GOLD + s + ChatColor.WHITE + " as race!");
//                            db.setRace(player, s);
                                List<String> arg = new ArrayList<>();
                                arg.add(p.getName());
                                arg.add(s);
                                arg.add(String.valueOf(RaceConfig.getInstance().getAddPointsOnRaceSelect()));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceStrength(s)));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceDexterity(s)));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceConstitution(s)));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceWisdom(s)));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceIntelligence(s)));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceCharisma(s)));
                                arg.add(String.valueOf(RaceConfig.getInstance().getRaceLuck(s)));
                                PlayerData.setSetterData(p, "onRacePick", "UserModel", arg);
                                match = true;
                            }
                        }
                    }
                    if (!match) {
                        if (races == null) {
                            p.sendMessage(clover.cloverprefix + "The server owner did not add any races yet. ");
                        } else {
                            p.sendMessage(clover.cloverprefix + "Use /race select [race name] and pick one of the following races:");
                            for (String s : races) {
                                p.sendMessage("» " + ChatColor.GOLD + s);
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(clover.cloverprefix + ChatColor.RED + "Invalid input" + ChatColor.WHITE + " did you mean /race select?");
            }
        }
        return false;
    }
}
