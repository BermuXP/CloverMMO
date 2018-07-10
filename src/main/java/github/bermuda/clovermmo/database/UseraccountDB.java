package github.bermuda.clovermmo.database;

public class UseraccountDB {
    public String race;
    public String pclass;
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

    public UseraccountDB() {
    }

    public void initialize(String grace, String gpclass, String gspec, String gfaction, int gpoint, int gstrength, int gdexterity, int gconstitution, int gwisdom, int gcharisma, int gintelligence, int gluck) {
        this.race = grace;
        this.pclass = gpclass;
        this.spec = gspec;
        this.faction = gfaction;
        this.point = gpoint;
        this.strength = gstrength;
        this.dexterity = gdexterity;
        this.constitution = gconstitution;
        this.wisdom = gwisdom;
        this.charisma = gcharisma;
        this.intelligence = gintelligence;
        this.luck = gluck;
    }

    //Setters
    public void setRace(String race) {
        this.race = race;
    }

    public void setPclass(String pclass) {
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

    //getters
    public String getRace() {
        return race;
    }

    public String getPclass() {
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
}

