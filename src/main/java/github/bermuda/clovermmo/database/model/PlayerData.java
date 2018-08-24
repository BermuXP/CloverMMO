package github.bermuda.clovermmo.database.model;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;
import java.util.*;

import static github.bermuda.clovermmo.CloverMMO.clover;

public class PlayerData {
    public static Map<String, List<Object>> Players;
    public static Map<String, Object> GlobalModels;

    public PlayerData() {
        // when server starts this gets called.
        // when called makes hashmap for players that join to save their model
        Players = new HashMap<>();
        GlobalModels = new HashMap<>();
    }

    public static void onPlayerJoin(Player player) {
        //happens on player join
        List<Object> m = new ArrayList<>();
        m.add(new UserModel());
        Players.put(String.valueOf(player.getUniqueId()), m);
    }

    public static void onServerStart() {
        List<Object> d = new ArrayList<>();
        d.add(new RacesModel());

    }

    public static void addGlobalModal(String name, Object modal) {
        //name - name of the model
        //modal - informatie
        GlobalModels.put(name, modal);
    }

    public static void setSetterData(Player player, String function, String file, List<String> arg) {
        List<Object> models = Players.get(String.valueOf(player.getUniqueId()));
        int key = moderliser(file);
        try {
            Class[] klas = new Class[arg.size()];
            Object[] Hack = new Object[arg.size()];
            int i = 0;
            for (String a : arg) {
                klas[i] = String.class;
                Hack[i] = a;
                i++;
            }
            player.sendMessage(clover.cloverprefix + "function and klas " + function + " " + Arrays.toString(klas) + " " + Arrays.toString(Hack));
            Method iets = models.get(key).getClass().getMethod(function, klas);
            player.sendMessage(clover.cloverprefix + "end " + iets.toString());
            iets.invoke(models.get(key), Hack);
        } catch (Exception e) {
            player.sendMessage(clover.cloverprefix + "Error " + e.toString());
        }
    }

    public static void getGetterData() {

    }

    public static void setOtherSetterData(String function, String file, List<Object> arg) {
//        Object model = GlobalModels.get(file);
//        model[]
    }

    private static int moderliser(String model) {
        switch (model) {
            case "UserModel":
                return 0;
            default:
                return 0;
        }
    }

}
