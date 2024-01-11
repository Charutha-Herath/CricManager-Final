package lk.ijse.cric.dto;

public class CoachDto {
    private String CId;
    private String CUId;
    private String name;
    private String type;

    public CoachDto(String CId, String CUId, String name, String type) {
        this.CId = CId;
        this.CUId = CUId;
        this.name = name;
        this.type = type;
    }

    public CoachDto(String updateId, String updateName, String updateCategory) {
        this.CId = updateId;
        this.name = updateName;
        this.type = updateCategory;
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
        return "CoachDto{" +
                "CId='" + CId + '\'' +
                ", CUId='" + CUId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
