package lk.ijse.cric.dto;

public class UserDto {
    private String UId;
    private String UName;
    private String Pwd;
    private String StartDate;

    public UserDto(String UId, String UName, String pwd, String startDate) {
        this.UId = UId;
        this.UName = UName;
        Pwd = pwd;
        StartDate = startDate;
    }

    public UserDto(String UId, String UName, String startDate) {
        this.UId = UId;
        this.UName = UName;
        StartDate = startDate;
    }

    public UserDto(String username, String password) {
        this.UName = username;
        this.Pwd = password;
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
}
