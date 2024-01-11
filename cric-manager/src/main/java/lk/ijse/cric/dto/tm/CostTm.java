package lk.ijse.cric.dto.tm;

public class CostTm {
    private String CostId;
    private String CostMId;
    private double ground_fee;
    private double umpire_fee;
    private double equipment_fee;
    private double meal_and_other;
    private double total;

    public CostTm(String costId, String costMId, double ground_fee, double umpire_fee, double equipment_fee, double meal_and_other, double total) {
        CostId = costId;
        CostMId = costMId;
        this.ground_fee = ground_fee;
        this.umpire_fee = umpire_fee;
        this.equipment_fee = equipment_fee;
        this.meal_and_other = meal_and_other;
        this.total = total;
    }

    public String getCostId() {
        return CostId;
    }

    public void setCostId(String costId) {
        CostId = costId;
    }

    public String getCostMId() {
        return CostMId;
    }

    public void setCostMId(String costMId) {
        CostMId = costMId;
    }

    public double getGround_fee() {
        return ground_fee;
    }

    public void setGround_fee(double ground_fee) {
        this.ground_fee = ground_fee;
    }

    public double getUmpire_fee() {
        return umpire_fee;
    }

    public void setUmpire_fee(double umpire_fee) {
        this.umpire_fee = umpire_fee;
    }

    public double getEquipment_fee() {
        return equipment_fee;
    }

    public void setEquipment_fee(double equipment_fee) {
        this.equipment_fee = equipment_fee;
    }

    public double getMeal_and_other() {
        return meal_and_other;
    }

    public void setMeal_and_other(double meal_and_other) {
        this.meal_and_other = meal_and_other;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CostTm{" +
                "CostId='" + CostId + '\'' +
                ", CostMId='" + CostMId + '\'' +
                ", ground_fee=" + ground_fee +
                ", umpire_fee=" + umpire_fee +
                ", equipment_fee=" + equipment_fee +
                ", meal_and_other=" + meal_and_other +
                ", total=" + total +
                '}';
    }
}
