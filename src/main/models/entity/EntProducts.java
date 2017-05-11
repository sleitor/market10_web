package main.models.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by User on 10.05.2017.
 */
@Entity
@Table(name = "products", schema = "demo", catalog = "")
public class EntProducts {
    private long uuid;
    private String name;
    private String description;
    private Integer quantity;
    private Integer cost;
    private Collection<EntOrderProducts> orderProductsByUuid;

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
    @Column(name = "name", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "cost", nullable = true)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntProducts that = (EntProducts) o;

        if (uuid != that.uuid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uuid ^ (uuid >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productsByUuidProduct")
    public Collection<EntOrderProducts> getOrderProductsByUuid() {
        return orderProductsByUuid;
    }

    public void setOrderProductsByUuid(Collection<EntOrderProducts> orderProductsByUuid) {
        this.orderProductsByUuid = orderProductsByUuid;
    }
}
