package lk.ijse.cric.entity;

public class Umpire {

    private String UmpId;
    private String UmpUId;
    private String name;

    public Umpire(String umpId, String umpUId, String name) {
        UmpId = umpId;
        UmpUId = umpUId;
        this.name = name;
    }

    public Umpire(String umpId, String name) {
        UmpId = umpId;
        this.name = name;
    }

    public String getUmpId() {
        return UmpId;
    }

    public void setUmpId(String umpId) {
        UmpId = umpId;
    }

    public String getUmpUId() {
        return UmpUId;
    }

    public void setUmpUId(String umpUId) {
        UmpUId = umpUId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Umpire{" +
                "UmpId='" + UmpId + '\'' +
                ", UmpUId='" + UmpUId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
