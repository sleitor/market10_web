package main.models.pojo;

import java.sql.Date;
import java.util.List;

public class Order {

    private long uuid;
    private long uuid_user;
    private String login_user;
    private Date date;
    private List<OrderProduct> orderProducts;
    private float cost;
    private String status;


    public Order() {
    }

    public Order(long uuid, long uuid_user, Date date, float cost, String status) {
        this.uuid = uuid;

        this.uuid_user = uuid_user;
        this.date = date;
        this.cost = cost;
        this.status = status;
    }

    public String getLogin_user() {
        return login_user;
    }

    public void setLogin_user(String login_user) {
        this.login_user = login_user;
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

    public void setUuid_user(long uuid_user) {
        this.uuid_user = uuid_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(uuid) * 32;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
