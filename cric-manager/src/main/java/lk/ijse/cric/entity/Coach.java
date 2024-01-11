package lk.ijse.cric.entity;

public class Coach {

    private String CId;
    private String CUId;
    private String name;
    private String type;

    private String totalCoaches;

    public Coach(String CId, String CUId, String name, String type) {
        this.CId = CId;
        this.CUId = CUId;
        this.name = name;
        this.type = type;
    }

    public Coach(String updateId, String updateName, String updateCategory) {
        this.CId = updateId;
        this.name = updateName;
        this.type = updateCategory;

    }
    public Coach(String rowCount) {
        this.totalCoaches = rowCount;
    }




    public String getTotalCoaches() {
        return totalCoaches;
    }

    public void setTotalCoaches(String totalCoaches) {
        this.totalCoaches = totalCoaches;
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
        return "Coach{" +
                "CId='" + CId + '\'' +
                ", CUId='" + CUId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", totalCoaches='" + totalCoaches + '\'' +
                '}';
    }

}
