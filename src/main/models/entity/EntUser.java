package main.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by User on 10.05.2017.
 */
@Entity
@Table(name = "users", schema = "demo")
public class EntUser implements Serializable {
    private Long uuid;
    private String userName;
    private String email;
    private String firstName;
    private String secondName;
    private String lastName;
    private String address;
    private String password;
    private int role;
    private int enabled;
    private Collection<EntOrder> ordersByUuid;
    private Collection<EntUserRoles> userRolesByUserName;

    public EntUser() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uuid", nullable = false)
    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
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

        EntUser EntUser = (EntUser) o;

        if (uuid != EntUser.uuid) return false;
        if (role != EntUser.role) return false;
        if (enabled != EntUser.enabled) return false;
        if (userName != null ? !userName.equals(EntUser.userName) : EntUser.userName != null) return false;
        if (email != null ? !email.equals(EntUser.email) : EntUser.email != null) return false;
        if (firstName != null ? !firstName.equals(EntUser.firstName) : EntUser.firstName != null) return false;
        if (secondName != null ? !secondName.equals(EntUser.secondName) : EntUser.secondName != null) return false;
        if (lastName != null ? !lastName.equals(EntUser.lastName) : EntUser.lastName != null) return false;
        if (address != null ? !address.equals(EntUser.address) : EntUser.address != null) return false;
        if (password != null ? !password.equals(EntUser.password) : EntUser.password != null) return false;

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
    public Collection<EntOrder> getOrdersByUuid() {
        return ordersByUuid;
    }

    public void setOrdersByUuid(Collection<EntOrder> ordersByUuid) {
        this.ordersByUuid = ordersByUuid;
    }

    @OneToMany(mappedBy = "userRolesByUserName", cascade = CascadeType.ALL)
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
