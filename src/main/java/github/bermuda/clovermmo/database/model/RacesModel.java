package github.bermuda.clovermmo.database.model;

import java.util.List;

public class RacesModel {

    public List<Integer> id;
    public List<String> RaceName;
    public List<Integer> point;
    public List<Integer> strength;
    public List<Integer> dexterity;
    public List<Integer> constitution;
    public List<Integer> wisdom;
    public List<Integer> charisma;
    public List<Integer> intelligence;
    public List<Integer> luck;
//
//    public RacesModel() {
//         List<Integer> id;
//         List<String> RaceName;
//         List<Integer> point;
//         List<Integer> strength;
//         List<Integer> dexterity;
//         List<Integer> constitution;
//         List<Integer> wisdom;
//         List<Integer> charisma;
//         List<Integer> intelligence;
//         List<Integer> luck;
//    }

    //    public void race(List<Integer> gid, List<String> grace, List<Integer> gpoint, List<Integer> gstrength, List<Integer> gdexterity, List<Integer> gconstitution, List<Integer> gwisdom, List<Integer> gcharisma, List<Integer> gintelligence, List<Integer> gluck)
    public void race(int gid, String grace, int gpoint, int gstrength, int gdexterity, int gconstitution, int gwisdom, int gcharisma, int gintelligence, int gluck) {
        this.id.add(gid);
        this.RaceName.add(grace);
        this.point.add(gpoint);
        this.strength.add(gstrength);
        this.dexterity.add(gdexterity);
        this.constitution.add(gconstitution);
        this.wisdom.add(gwisdom);
        this.charisma.add(gcharisma);
        this.intelligence.add(gintelligence);
        this.luck.add(gluck);
    }

    //Setters
    public void setRaceName(List<String> RaceName) {
        this.RaceName = RaceName;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public void setPoint(List<Integer> point) {
        this.point = point;
    }

    public void setStrength(List<Integer> strength) {
        this.strength = strength;
    }

    public void setDexterity(List<Integer> dexterity) {
        this.dexterity = dexterity;
    }

    public void setConstitution(List<Integer> constitution) {
        this.constitution = constitution;
    }

    public void setWisdom(List<Integer> wisdom) {
        this.wisdom = wisdom;
    }

    public void setCharisma(List<Integer> charisma) {
        this.charisma = charisma;
    }

    public void setIntelligence(List<Integer> intelligence) {
        this.intelligence = intelligence;
    }

    public void setLuck(List<Integer> luck) {
        this.luck = luck;
    }

    //getters
    public List<String> getRaceName() {
        return RaceName;
    }

    public List<Integer> getId() {
        return id;
    }

    public List<Integer> getPoint() {
        return point;
    }

    public List<Integer> getStrength() {
        return strength;
    }

    public List<Integer> getDexterity() {
        return dexterity;
    }

    public List<Integer> getConstitution() {
        return constitution;
    }

    public List<Integer> getWisdom() {
        return wisdom;
    }

    public List<Integer> getCharisma() {
        return charisma;
    }

    public List<Integer> getIntelligence() {
        return intelligence;
    }

    public List<Integer> getLuck() {
        return luck;
    }
}



