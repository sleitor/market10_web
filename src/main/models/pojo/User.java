package main.models.pojo;

import java.util.HashSet;

public class User {

    private Long uuid;
    private String userName;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String address;
    private String password;
    private HashSet<Order> orders;
    @Deprecated
    private boolean role;

    public User() {
    }

    public User(Long uuid, String userName, String email, String firstName, String secondName, String lastName, String address, String password, boolean role) {
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

    public void setOrders(HashSet<Order> orders) {
        this.orders = orders;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(uuid);
    }
}
