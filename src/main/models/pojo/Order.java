package main.models.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;

public class Order {

    private long uuid;
    private long uuid_user;
    private Date date;
    private ArrayList<OrderProduct> orderProducts;

    private float cost;
    private String status;

    public void setCost(float cost) {
        this.cost = cost;
    }

    public long getUuid() {
        return uuid;
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

    public String getStatus() {
        return status;
    }

    public void setOrderProducts(ArrayList<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Order(long uuid, long uuid_user, Date date, float cost, String status) {
        this.uuid = uuid;

        this.uuid_user = uuid_user;
        this.date = date;
        this.cost = cost;
        this.status = status;
    }

    public ArrayList<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }
}
