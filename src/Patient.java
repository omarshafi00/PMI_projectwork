/**
 * Class of Patient Element
 */
public class Patient {

    private int idNumber;
    private String name;
    private int age;
    private String gender;

    public Patient(int idNumber, String name, int age, String gender){
        this.idNumber = idNumber;
        this.name = name;
        this.age = age;
        this.gender = gender;

    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }



    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Patient ID Number : " + idNumber +
                ", Name : " + name +
                ", Age : " + age+
                ", Gender : " + gender;
    }
}
