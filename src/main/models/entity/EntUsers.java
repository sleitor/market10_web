package main.models.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by User on 10.05.2017.
 */
@Entity
@Table(name = "users", schema = "demo")
public class EntUsers {
    private long uuid;
    private String userName;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String address;
    private String password;
    private int role;
    private int enabled;
    private Collection<EntOrders> ordersByUuid;
    private Collection<EntUserRoles> userRolesByUserName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "userName", nullable = true, length = 255)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "firstName", nullable = true, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "secondName", nullable = true, length = 255)
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "lastName", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 255)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    @Basic
//    @Column(name = "role", nullable = false)
//    public int getRole() {
//        return role;
//    }
//
//    public void setRole(int role) {
//        this.role = role;
//    }

    @Basic
    @Column(name = "enabled", nullable = false)
    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntUsers EntUsers = (EntUsers) o;

        if (uuid != EntUsers.uuid) return false;
        if (role != EntUsers.role) return false;
        if (enabled != EntUsers.enabled) return false;
        if (userName != null ? !userName.equals(EntUsers.userName) : EntUsers.userName != null) return false;
        if (email != null ? !email.equals(EntUsers.email) : EntUsers.email != null) return false;
        if (firstName != null ? !firstName.equals(EntUsers.firstName) : EntUsers.firstName != null) return false;
        if (secondName != null ? !secondName.equals(EntUsers.secondName) : EntUsers.secondName != null) return false;
        if (lastName != null ? !lastName.equals(EntUsers.lastName) : EntUsers.lastName != null) return false;
        if (address != null ? !address.equals(EntUsers.address) : EntUsers.address != null) return false;
        if (password != null ? !password.equals(EntUsers.password) : EntUsers.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uuid ^ (uuid >>> 32));
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + role;
        result = 31 * result + enabled;
        return result;
    }

    @OneToMany(mappedBy = "usersByUuidUser")
    public Collection<EntOrders> getOrdersByUuid() {
        return ordersByUuid;
    }
    public void setOrdersByUuid(Collection<EntOrders> ordersByUuid) {
        this.ordersByUuid = ordersByUuid;
    }

    @OneToMany(mappedBy = "userRolesByUserName")
    public Collection<EntUserRoles> getUserRolesByUserName() {
        return userRolesByUserName;
    }
    public void setUserRolesByUserName(Collection<EntUserRoles> userRolesByUserName) {
        this.userRolesByUserName = userRolesByUserName;
    }
    //
//    @ManyToMany
//    @JoinColumn(name = "userName", referencedColumnName = "login")
//    public Collection<EntUserRoles> getUserRolesByUserName() {
//        return userRolesByUserName;
//    }
//
//    public void setUserRolesByUserName(Collection<EntUserRoles> userRolesByUserName) {
//        this.userRolesByUserName = userRolesByUserName;
//    }

}
