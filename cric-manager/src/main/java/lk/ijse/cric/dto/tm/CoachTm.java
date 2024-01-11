package lk.ijse.cric.dto.tm;

public class CoachTm {
    private String CId;
    private String CUId ="A001";
    private String name;
    private String type;

    public CoachTm(String CId, String CUId, String name, String type) {
        this.CId = CId;
        this.CUId = "A001";
        this.name = name;
        this.type = type;
    }

    public String getCId() {
        return CId;
    }

    public void setCId(String CId) {
        this.CId = CId;
    }

    public String getCUId() {
        return CUId;
    }

    public void setCUId(String CUId) {
        this.CUId = CUId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CoachTm{" +
                "CId='" + CId + '\'' +
                ", CUId='" + CUId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
