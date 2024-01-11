package lk.ijse.cric.dto;

public class DonationDto {
    private String DId;
    private String type;
    private double value;
    private String desc;

    private String Date;

    public DonationDto(String DId, String type, double value, String desc,String date) {
        this.DId = DId;
        this.type = type;
        this.value = value;
        this.desc = desc;
        this.Date = date;
    }

    public String getDId() {
        return DId;
    }

    public void setDId(String DId) {
        this.DId = DId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "DonationDto{" +
                "DId='" + DId + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", desc='" + desc + '\'' +
                '}';
    }
}
