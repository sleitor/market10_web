package main.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entity of OrderProduct for JPA
 */
@Entity
@Table(name = "orderProducts", schema = "demo")
public class EntOrderProduct implements Serializable {
    private Long uuid;
    private long uuidOrder;
    private long uuidProduct;
    private Integer count;
    private Float cost;
    private EntOrder ordersByUuidOrder;
    private EntProduct productsByUuidProduct;
    private String name_product;

    public EntOrderProduct() {
    }

    @Basic
    @Column(name = "count")
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "cost")
    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }


    @Basic
    @Column(name = "name_product")
    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntOrderProduct that = (EntOrderProduct) o;

        if (uuidOrder != that.uuidOrder) return false;
        if (uuidProduct != that.uuidProduct) return false;
        if (!Objects.equals(uuid, that.uuid)) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uuidOrder ^ (uuidOrder >>> 32));
        result = 31 * result + (int) (uuidProduct ^ (uuidProduct >>> 32));
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (int) (uuid ^ (uuid >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "uuid_order", referencedColumnName = "uuid", nullable = false)
    public EntOrder getOrdersByUuidOrder() {
        return ordersByUuidOrder;
    }

    public void setOrdersByUuidOrder(EntOrder ordersByUuidOrder) {
        this.ordersByUuidOrder = ordersByUuidOrder;
    }

    @ManyToOne
    @JoinColumn(name = "uuid_product", referencedColumnName = "uuid", nullable = false)
    public EntProduct getProductsByUuidProduct() {
        return productsByUuidProduct;
    }

    public void setProductsByUuidProduct(EntProduct productsByUuidProduct) {
        this.productsByUuidProduct = productsByUuidProduct;
    }
}
