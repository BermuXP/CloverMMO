package bermuda.database.models;

import java.util.HashMap;

public class SubclassModel {
    private int id;
    private String name;
    private int statsId;
    private String description;
    private int classId;

    public SubclassModel(HashMap<String, String> data) {
        this.id = Integer.parseInt(data.get("id"));
        this.name = data.get("name");
        this.statsId = Integer.parseInt(data.get("stat_id"));
        this.description = data.get("description");
        this.classId = Integer.parseInt(data.get("class_id"));
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatsId(int statsId) {
        this.statsId = statsId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    //Getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStatsId() {
        return this.statsId;
    }

    public String getDescription() {
        return description;
    }

    public int getClassId() {
        return classId;
    }


}
