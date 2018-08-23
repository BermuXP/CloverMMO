package github.bermuda.clovermmo.database.data;

import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.*;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class PlayerData {
    public static Map<String, List<Object>> Players;

    public PlayerData() {
        // when server starts this gets called.
        // when called makes hashmap for players that join to save their data
        Players = new HashMap<String, List<Object>>();
    }

    public static void onPlayerJoin(Player player) {
        //happens on player join
        List<Object> m = new ArrayList<Object>();
        m.add(new UserData());
        Players.put(String.valueOf(player.getUniqueId()), m);
    }

    public static void getVariable(Player player, String function, String object, List<String> arg) {
        List<Object> getModels = Players.get(String.valueOf(player.getUniqueId()));
        int key = moderliser(object);
        try {
            Class[] klas = new Class[arg.size()];
            Object[] Hack = new Object[arg.size()];
            int i = 0;
            for (String a : arg) {
                klas[i] = String.class;
                Hack[i] = a;
            }
            player.sendMessage(clover.cloverprefix + "function and klas " + function + " " + Arrays.toString(klas));
            Method iets = getModels.get(key).getClass().getMethod(function, klas);
            player.sendMessage(clover.cloverprefix + "end " + iets.toString());
            iets.invoke(getModels.get(key), Hack);
        } catch (Exception e) {
            player.sendMessage(clover.cloverprefix + "Error " + e.toString());
        }
    }



    private static int moderliser(String model) {
        switch (model) {
            case "UserData":
                return 0;
            default:
                return 0;
        }
    }

}
