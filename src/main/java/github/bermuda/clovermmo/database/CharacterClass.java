package github.bermuda.clovermmo.database;

public class CharacterClass {
    public int strength;
    public int dexterity;
    public int constitution;
    public int wisdom;
    public int charisma;
    public int intelligence;
    public int luck;

    public CharacterClass() {

    }

    public void initialize(int gstrength, int gdexterity, int gconstitution, int gwisdom, int gcharisma, int gintelligence, int gluck) {
        this.strength = gstrength;
        this.dexterity = gdexterity;
        this.constitution = gconstitution;
        this.wisdom = gwisdom;
        this.charisma = gcharisma;
        this.intelligence = gintelligence;
        this.luck = gluck;
    }

    //Setters
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

