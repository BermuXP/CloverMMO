package github.bermuda.clovermmo.API.placeholder;

import github.bermuda.clovermmo.database.SQLite;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.clover;
import static github.bermuda.clovermmo.CloverMMO.db;

public class Placeholder {

    public static StringBuffer onPlaceholderRequest(Player player, String s) {
        db = new SQLite(clover);
        db.load();
        db.getUserData(player);

        Map<String,String> token = new HashMap<>();
        token.put("displayname", player.getDisplayName());
        token.put("playername", player.getName());
        token.put("faction", cc.getFaction());
        token.put("race", cc.getRace());
        token.put("level", String.valueOf(cc.getLevel()));
        token.put("class", cc.getPclass());
        token.put("spec", cc.getSpec());
        token.put("strength", String.valueOf(cc.getStrength()));
        token.put("dexterity", String.valueOf(cc.getDexterity()));
        token.put("constitution", String.valueOf(cc.getConstitution()));
        token.put("wisdom", String.valueOf(cc.getWisdom()));
        token.put("intelligence", String.valueOf(cc.getIntelligence()));
        token.put("charisma", String.valueOf(cc.getCharisma()));
        token.put("luck", String.valueOf(cc.getLuck()));
        token.put("experience", String.valueOf(cc.getExp()));
        token.put("points", String.valueOf(cc.getPoint()));

        String patternString = "%(" + StringUtils.join(token.keySet(), "|") + ")%";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);

        StringBuffer sb = new StringBuffer();
        while(matcher.find()) {
            if(token.get(matcher.group(1)) == null) {
                matcher.appendReplacement(sb, "Not selected");
            } else {
                matcher.appendReplacement(sb, token.get(matcher.group(1)));
            }
        }
        matcher.appendTail(sb);
        System.out.println(sb.toString());
        return sb;
    }
}
