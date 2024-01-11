package lk.ijse.cric.dto;

public class LoginDto {

    private String UId;
    private String UName;
    private String Pwd;

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

    public LoginDto(){

    }

    public LoginDto(String UId, String UName, String pwd) {
        this.UId = UId;
        this.UName = UName;
        this.Pwd = pwd;
    }

    public LoginDto(String username, String password) {
        this.UName = username;
        this.Pwd = password;
    }


}
