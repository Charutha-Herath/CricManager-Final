package lk.ijse.cric.dto;

public class SponsorDto {
    private String SId;
    private String UId;
    private String name;
    private String company;
    private double value;
    private String Date;


    public SponsorDto(String SId, String UId, String name, String company, double value,String date) {
        this.SId = SId;
        this.UId = UId;
        this.name = name;
        this.company = company;
        this.value = value;
        this.Date=date;
    }

    public SponsorDto(String SId, String name, String company, double value) {
        this.SId = SId;
        this.name = name;
        this.company = company;
        this.value = value;
    }

    public SponsorDto(String name, String company, double value) {
        this.name = name;
        this.company = company;
        this.value = value;
    }

    public SponsorDto() {

    }

    public String getSId() {
        return SId;
    }

    public void setSId(String SId) {
        this.SId = SId;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "SponsorDto{" +
                "SId='" + SId + '\'' +
                ", UId='" + UId + '\'' +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", value=" + value +
                '}';
    }
}
