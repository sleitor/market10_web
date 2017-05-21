package main.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

/**
 * Entity of Order for JPA
 */
@Entity
@Table(name = "orders", schema = "demo")
public class EntOrder implements Serializable {
    private Long uuid;
    private Date date;
    private Float cost;
    private String status;
    private Collection<EntOrderProduct> orderProductsByUuid;
    private EntUser usersByUuidUser;

    public EntOrder() {
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
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "cost", nullable = true, precision = 0)
    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntOrder entOrder = (EntOrder) o;

        if (!Objects.equals(uuid, entOrder.uuid)) return false;
        if (date != null ? !date.equals(entOrder.date) : entOrder.date != null) return false;
        if (cost != null ? !cost.equals(entOrder.cost) : entOrder.cost != null) return false;
        if (status != null ? !status.equals(entOrder.status) : entOrder.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uuid ^ (uuid >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "ordersByUuidOrder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Transient
    public Collection<EntOrderProduct> getOrderProductsByUuid() {
        return orderProductsByUuid;
    }

    public void setOrderProductsByUuid(Collection<EntOrderProduct> orderProductsByUuid) {
        this.orderProductsByUuid = orderProductsByUuid;
    }

    @ManyToOne
    @JoinColumn(name = "uuid_user", referencedColumnName = "uuid", nullable = false)
    public EntUser getUsersByUuidUser() {
        return usersByUuidUser;
    }

    public void setUsersByUuidUser(EntUser usersByUuidUser) {
        this.usersByUuidUser = usersByUuidUser;
    }
}
