package bermuda.database.models;

public class RacesModel {
    private int id;
    private String name;
    private int stats_id;
    private String description;

    public RacesModel(int id, String name, int stats_id, String description) {
        this.id = id;
        this.name = name;
        this.stats_id = stats_id;
        this.description = description;
    }

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStats_id(int stats_id) {
        this.stats_id = stats_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //Getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getStats_id() {
        return this.stats_id;
    }

    public String getDescription() {
        return description;
    }
}
