package lk.ijse.cric.dto.tm;

public class UserDashTm {
    private String UId;
    private String UName;
    private String Pwd;
    private  String StartDate;

    public UserDashTm(String UId, String UName, String pwd, String startDate) {
        this.UId = UId;
        this.UName = UName;
        Pwd = pwd;
        StartDate = startDate;
    }

    public String getUId() {
        return UId;
    }

    public void setUId(String UId) {
        this.UId = UId;
    }

    public String getUName() {
        return UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }

    public String getPwd() {
        return Pwd;
    }

    public void setPwd(String pwd) {
        Pwd = pwd;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    @Override
    public String toString() {
        return "UserDashTm{" +
                "UId='" + UId + '\'' +
                ", UName='" + UName + '\'' +
                ", Pwd='" + Pwd + '\'' +
                ", StartDate='" + StartDate + '\'' +
                '}';
    }
}
