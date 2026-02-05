package sef.module11.activity;



public class User {
    private final int id;
    private final String email;
    private final int age;

    public User(int id, String email, int age) {
        this.id = id;
        this.email = email;
        this.age = age;
    }

    public int getId() { return id; }
    public String getEmail() { return email; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return "User{id=" + id + ", email='" + email + "', age=" + age + "}";
    }
}
