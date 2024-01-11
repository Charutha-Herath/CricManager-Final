package lk.ijse.cric.dto.tm;

import javafx.scene.control.Button;

public class PlayerTm {
    private String PId;
    private String PUId;
    private String name;
    private int age;
    private String type;

    private Button btn;

    public PlayerTm(String PId, String PUId, String name, int age, String type) {
        this.PId = PId;
        this.PUId = PUId;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public PlayerTm(String PId, String name, int age, String type,Button btn) {
        this.PId = PId;

        this.name = name;
        this.age = age;
        this.type = type;
        this.btn = btn;
    }

    public String getPId() {
        return PId;
    }

    public void setPId(String PId) {
        this.PId = PId;
    }

    public String getPUId() {
        return PUId;
    }

    public void setPUId(String PUId) {
        this.PUId = PUId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "PlayerTm{" +
                "PId='" + PId + '\'' +
                ", PUId='" + PUId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                ", btn=" + btn +
                '}';
    }
}
