package main.models.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by User on 10.05.2017.
 */
@Entity
@Table(name = "user_roles", schema = "demo", catalog = "")
public class EntUserRoles {
    private long uuid;
    private String login;
    private String roles;
    private Collection<EntUsers> userRolesByUserName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    @Basic
    @Column(name = "login", nullable = false, length = 255)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "roles", nullable = true, length = 30)
    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntUserRoles that = (EntUserRoles) o;

        if (uuid != that.uuid) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uuid ^ (uuid >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userRolesByUserName")
    public Collection<EntUsers> getUserRolesByUserName() {
        return userRolesByUserName;
    }

    public void setUserRolesByUserName(Collection<EntUsers> userRolesByUserName) {
        this.userRolesByUserName = userRolesByUserName;
    }
}
