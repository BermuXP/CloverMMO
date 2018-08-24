package github.bermuda.clovermmo.API.placeholder;

import github.bermuda.clovermmo.config.setconfig.ProfileConfig;
import github.bermuda.clovermmo.config.setconfig.RaceConfig;
import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static github.bermuda.clovermmo.CloverMMO.cc;
import static github.bermuda.clovermmo.CloverMMO.db;

public class Placeholder {

    public static StringBuffer onPlaceholderRequest(String m, Player player, String s) {
        db.onRacePickDB(player);

        String nextlevel = String.valueOf(ProfileConfig.profile().getExp(cc.getLevel()));

        Map<String, String> token = new HashMap<>();

        token.put("racestrength", String.valueOf(RaceConfig.getInstance().getRaceStrength(m)));
        token.put("racedexterity", String.valueOf(RaceConfig.getInstance().getRaceDexterity(m)));
        token.put("raceconstitution", String.valueOf(RaceConfig.getInstance().getRaceConstitution(m)));
        token.put("raceintelligence", String.valueOf(RaceConfig.getInstance().getRaceIntelligence(m)));
        token.put("racewisdom", String.valueOf(RaceConfig.getInstance().getRaceWisdom(m)));
        token.put("racecharisma", String.valueOf(RaceConfig.getInstance().getRaceCharisma(m)));
        token.put("raceluck", String.valueOf(RaceConfig.getInstance().getRaceLuck(m)));

        token.put("displayname", player.getDisplayName());
        token.put("playername", player.getName());
        token.put("faction", cc.getFaction());
        token.put("race", String.valueOf(cc.getRace()));
        token.put("level", String.valueOf(cc.getLevel()));

        if (nextlevel.equals("0")) {
            token.put("nextlevel", "Max");
        } else {
            token.put("nextlevel", nextlevel);
        }
//        token.put("nextlevel", String.valueOf(ProfileConfig.profile().getExp(cc.getLevel())));
        token.put("class", String.valueOf(cc.getPclass()));
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
        while (matcher.find()) {
            if (token.get(matcher.group(1)) == null) {
                matcher.appendReplacement(sb, "Not selected");
            } else {
                matcher.appendReplacement(sb, token.get(matcher.group(1)));
            }
        }
        matcher.appendTail(sb);
        return sb;
    }
}
