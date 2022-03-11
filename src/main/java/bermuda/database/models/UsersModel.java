package bermuda.database.models;

public class UsersModel {
    private int id;
    private String uuid;

    public void setId(int id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return this.id;
    }

    public String getUuid() {
        return this.uuid;
    }

}
