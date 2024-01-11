package lk.ijse.cric.dto.tm;

import javafx.scene.control.Button;

public class UmpireTm {
    private String UmpId;
    private String UmpUId;
    private String name;
    private Button btn;

    public UmpireTm(String umpId, String umpUId, String name, Button btn1) {
        UmpId = umpId;
        UmpUId = umpUId;
        this.name = name;
        this.btn = btn1;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "UmpireTm{" +
                "UmpId='" + UmpId + '\'' +
                ", UmpUId='" + UmpUId + '\'' +
                ", name='" + name + '\'' +
                ", btn=" + btn +
                '}';
    }
}
