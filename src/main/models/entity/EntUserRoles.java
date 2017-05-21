package main.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity of User roles for JPA
 */
@Entity
@Table(name = "user_roles", schema = "demo")
public class EntUserRoles implements Serializable {
    private Long uuid;
    private String login;
    private String roles;
    private EntUser userRolesByUserName;

    public EntUserRoles() {
    }

    public EntUserRoles(String login, String roles) {
        this.login = login;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
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

        if (!Objects.equals(uuid, that.uuid)) return false;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "login", referencedColumnName = "userName", insertable = false, updatable = false)
    public EntUser getUserRolesByUserName() {
        return userRolesByUserName;
    }

    public void setUserRolesByUserName(EntUser userRolesByUserName) {
        this.userRolesByUserName = userRolesByUserName;
    }
}
