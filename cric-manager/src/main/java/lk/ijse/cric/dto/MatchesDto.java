package lk.ijse.cric.dto;

public class MatchesDto {
    private String MId;
    private String MUId;

    private String ground;
    private String opposing_team;
    private String Date;
    private double est_cost;
    private double balance;

    private String Status;

    public MatchesDto(String MId, String MUId, String ground, String opposing_team, String date, double est_cost, double balance, String status) {
        this.MId = MId;
        this.MUId = MUId;
        this.ground = ground;
        this.opposing_team = opposing_team;
        Date = date;
        this.est_cost = est_cost;
        this.balance = balance;
        Status = status;
    }

    public MatchesDto(String MId, String ground, String opposing_team, String date, double est_cost, double balance, String status) {
        this.MId = MId;
        this.ground = ground;
        this.opposing_team = opposing_team;
        Date = date;
        this.est_cost = est_cost;
        this.balance = balance;
        Status = status;
    }

    public MatchesDto(String mid, String ground, String oppo, double est, double balance, String date, String status) {
        this.MId = mid;
        this.ground = ground;
        this.opposing_team = oppo;
        Date = date;
        this.est_cost = est;
        this.balance = balance;
        Status = status;
    }

    public MatchesDto(String MId, String MUId, String ground, String oppoTeam, double estCost, String status, String date) {
        this.MId = MId;
        this.MUId = MUId;
        this.ground = ground;
        this.opposing_team = oppoTeam;
        this.est_cost = estCost;
        Status = status;
        Date = date;
    }

    public MatchesDto(String newMId, String ground, String oppoTeam, double estCost, String date, String status) {
        this.MId = newMId;
        this.ground = ground;
        this.opposing_team = oppoTeam;
        this.est_cost = estCost;
        Status = status;
        Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getMId() {
        return MId;
    }

    public void setMId(String MId) {
        this.MId = MId;
    }

    public String getMUId() {
        return MUId;
    }

    public void setMUId(String MUId) {
        this.MUId = MUId;
    }


    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getOpposing_team() {
        return opposing_team;
    }

    public void setOpposing_team(String opposing_team) {
        this.opposing_team = opposing_team;
    }

    public double getEst_cost() {
        return est_cost;
    }

    public void setEst_cost(double est_cost) {
        this.est_cost = est_cost;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "MatchesDto{" +
                "MId='" + MId + '\'' +
                ", MUId='" + MUId + '\'' +
                ", ground='" + ground + '\'' +
                ", opposing_team='" + opposing_team + '\'' +
                ", Date='" + Date + '\'' +
                ", est_cost=" + est_cost +
                ", balance=" + balance +
                ", Status='" + Status + '\'' +
                '}';
    }
}
