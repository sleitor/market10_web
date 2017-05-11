package main.models.pojo;

import java.sql.Date;
import java.util.ArrayList;

public class Order {

    private long uuid;
    private long uuid_user;
    private Date date;
    private ArrayList<OrderProduct> orderProducts;

    private float cost;
    private String status;

    public Order(long uuid, long uuid_user, Date date, float cost, String status) {
        this.uuid = uuid;

        this.uuid_user = uuid_user;
        this.date = date;
        this.cost = cost;
        this.status = status;
    }

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public long getUuid_user() {
        return uuid_user;
    }

    public Date getDate() {
        return date;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(uuid) * 32;
    }

    public ArrayList<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(ArrayList<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
