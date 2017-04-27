package main.models.pojo;

public class User {

    private long uuid;
    private String userName;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String address;
    private String password;
    private boolean role;

    public long getUuid() {
        return uuid;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRole() {
        return role;
    }

    public User(long uuid, String userName, String email, String firstName, String secondName, String lastName, String address, String password, boolean role) {
        this.uuid = uuid;
        this.userName = userName;
        this.email = email;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.role = role;
    }
}
