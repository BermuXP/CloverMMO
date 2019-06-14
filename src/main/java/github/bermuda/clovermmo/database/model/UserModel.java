package github.bermuda.clovermmo.database.model;

public class UserModel {
    public String playername;
    public int race;
    public int pclass;
    public String spec;
    public String faction;
    public int point;
    public int strength;
    public int dexterity;
    public int constitution;
    public int wisdom;
    public int charisma;
    public int intelligence;
    public int luck;
    public int level;
    public int exp;

    public void onRacePick(String gplayername, String grace, String gpoint, String gstrength, String gdexterity, String gconstitution, String gwisdom, String gcharisma, String gintelligence, String gluck) {
        this.playername = gplayername;
        this.race = Integer.parseInt(grace);
        this.point = Integer.parseInt(gpoint);
        this.strength = Integer.parseInt(gstrength);
        this.dexterity = Integer.parseInt(gdexterity);
        this.constitution = Integer.parseInt(gconstitution);
        this.wisdom = Integer.parseInt(gwisdom);
        this.charisma = Integer.parseInt(gcharisma);
        this.intelligence = Integer.parseInt(gintelligence);
        this.luck = Integer.parseInt(gluck);
    }

//    public void initialize(String gplayername, String grace, String gpclass, String gspec, String gfaction, String gpoint, String gstrength, String gdexterity, String gconstitution, String gwisdom, String gcharisma, String gintelligence, String gluck, String glevel, String gexp) {
//        this.playername = gplayername;
//        this.race = grace;
//        this.pclass = gpclass;
//        this.spec = gspec;
//        this.faction = gfaction;
//        this.point = Integer.parseInt(gpoint);
//        this.strength = Integer.parseInt(gstrength);
//        this.dexterity = Integer.parseInt(gdexterity);
//        this.constitution = Integer.parseInt(gconstitution);
//        this.wisdom = Integer.parseInt(gwisdom);
//        this.charisma = Integer.parseInt(gcharisma);
//        this.intelligence = Integer.parseInt(gintelligence);
//        this.luck = Integer.parseInt(gluck);
//        this.level = Integer.parseInt(glevel);
//        this.exp = Integer.parseInt(gexp);
//    }

    //Setters
    public void setPlayername(String playername) {
        this.playername = playername;
    }

    public void setRace(int race) {
        this.race = race;
    }

    public void setPclass(int pclass) {
        this.pclass = pclass;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    //getters
    public String getPlayername() {
        return playername;
    }

    public int getRace() {
        return race;
    }

    public int getPclass() {
        return pclass;
    }

    public String getFaction() {
        return faction;
    }

    public String getSpec() {
        return spec;
    }

    public int getPoint() {
        return point;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getLuck() {
        return luck;
    }

    public int getLevel() {
        return level;
    }

    public int getExp() {
        return exp;
    }
}

