package model;

public class Passenger {
    private static int nextId = 1; // Initialize to 1 or 0 depending on your preference
    private int id;
    private String name;
    private int age;
    private String gender;
    private String number;

    public Passenger(String name, int age, String gender,String number) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.number = number;
        this.id = nextId++;
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

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
