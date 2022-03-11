package bermuda.database.models;

public class ClassModel {
    private int id;
    private String name;
    private int statsId;
    private String description;

    public ClassModel(int id, String name, int statsId, String description) {
        this.id = id;
        this.name = name;
        this.statsId = statsId;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatsId(int statsId) {
        this.statsId = statsId;
    }

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

    public void setDescription(String description) {
        this.description = description;
    }
}
