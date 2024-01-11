package lk.ijse.cric.dto;

public class playerDto {
    private String PId;
    private String PUId;
    private String name;
    private int age;
    private String type;

    public playerDto(String PId, String PUId, String name, int age, String type) {
        this.PId = PId;
        this.PUId = PUId;
        this.name = name;
        this.age = age;
        this.type = type;
    }
    public playerDto(String PId, String name, int age, String type) {
        this.PId = PId;
        this.name = name;
        this.age = age;
        this.type = type;
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

    @Override
    public String toString() {
        return "playerDto{" +
                "PId='" + PId + '\'' +
                ", PUId='" + PUId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", type='" + type + '\'' +
                '}';
    }
}
