package main.models.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by User on 10.05.2017.
 */
@Entity
@Table(name = "orders", schema = "demo", catalog = "")
public class EntOrders {
    private long uuid;
    private long uuidUser;
    private Date date;
    private Double cost;
    private String status;
    private Collection<EntOrderProducts> orderProductsByUuid;
    private EntUsers usersByUuidUser;

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
    @Column(name = "uuid_user", nullable = false)
    public long getUuidUser() {
        return uuidUser;
    }

    public void setUuidUser(long uuidUser) {
        this.uuidUser = uuidUser;
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
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
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

        EntOrders entOrders = (EntOrders) o;

        if (uuid != entOrders.uuid) return false;
        if (uuidUser != entOrders.uuidUser) return false;
        if (date != null ? !date.equals(entOrders.date) : entOrders.date != null) return false;
        if (cost != null ? !cost.equals(entOrders.cost) : entOrders.cost != null) return false;
        if (status != null ? !status.equals(entOrders.status) : entOrders.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uuid ^ (uuid >>> 32));
        result = 31 * result + (int) (uuidUser ^ (uuidUser >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "ordersByUuidOrder")
    public Collection<EntOrderProducts> getOrderProductsByUuid() {
        return orderProductsByUuid;
    }

    public void setOrderProductsByUuid(Collection<EntOrderProducts> orderProductsByUuid) {
        this.orderProductsByUuid = orderProductsByUuid;
    }

    @ManyToOne
    @JoinColumn(name = "uuid_user", referencedColumnName = "uuid", nullable = false)
    public EntUsers getUsersByUuidUser() {
        return usersByUuidUser;
    }

    public void setUsersByUuidUser(EntUsers usersByUuidUser) {
        this.usersByUuidUser = usersByUuidUser;
    }
}
