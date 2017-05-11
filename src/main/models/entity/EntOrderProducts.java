package main.models.entity;

import javax.persistence.*;

/**
 * Created by User on 10.05.2017.
 */
@Entity
@Table(name = "orderProducts", schema = "demo", catalog = "")
public class EntOrderProducts {
    private long uuidOrder;
    private long uuidProduct;
    private Integer count;
    private Double cost;
    private long uuid;
    private EntOrders ordersByUuidOrder;
    private EntProduct productsByUuidProduct;


//    @Basic
//    @Column(name = "uuid_order", nullable = false)
//    public long getUuidOrder() {
//        return uuidOrder;
//    }
//
//    public void setUuidOrder(long uuidOrder) {
//        this.uuidOrder = uuidOrder;
//    }

//    @Basic
//    @Column(name = "uuid_product", nullable = false)
//    public long getUuidProduct() {
//        return uuidProduct;
//    }
//
//    public void setUuidProduct(long uuidProduct) {
//        this.uuidProduct = uuidProduct;
//    }

    @Basic
    @Column(name = "count", nullable = true)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Basic
    @Column(name = "cost", nullable = true, precision = 0)
    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uuid", nullable = false)
    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntOrderProducts that = (EntOrderProducts) o;

        if (uuidOrder != that.uuidOrder) return false;
        if (uuidProduct != that.uuidProduct) return false;
        if (uuid != that.uuid) return false;
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
    public EntOrders getOrdersByUuidOrder() {
        return ordersByUuidOrder;
    }

    public void setOrdersByUuidOrder(EntOrders ordersByUuidOrder) {
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
