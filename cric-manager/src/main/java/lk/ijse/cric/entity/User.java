package lk.ijse.cric.entity;

public class User {

    private String UId;
    private String UName;
    private String Pwd;
    private String StartDate;

    public User(String UId, String UName, String pwd, String startDate) {
        this.UId = UId;
        this.UName = UName;
        Pwd = pwd;
        StartDate = startDate;
    }

    public User(String uName, String pwd) {
        this.UName = uName;
        this.Pwd = pwd;
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
        return "User{" +
                "UId='" + UId + '\'' +
                ", UName='" + UName + '\'' +
                ", Pwd='" + Pwd + '\'' +
                ", StartDate='" + StartDate + '\'' +
                '}';
    }
}
