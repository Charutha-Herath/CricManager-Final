package lk.ijse.cric.dto;

public class UmpireDto {
    private String UmpId;
    private String UmpUId;
    private String name;

    public UmpireDto(String umpId, String umpUId, String name) {
        UmpId = umpId;
        UmpUId = umpUId;
        this.name = name;
    }

    public UmpireDto(String umpId, String name) {
        this.UmpId = umpId;
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
        return "UmpireDto{" +
                "UmpId='" + UmpId + '\'' +
                ", UmpUId='" + UmpUId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
