package main.models.pojo;

import java.sql.Date;

public class Order {

    private long uuid;
    private long uuid_user;
    private Date date;
    private float cost;

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

    public Order(long uuid, long uuid_user, Date date, float cost) {

        this.uuid = uuid;
        this.uuid_user = uuid_user;
        this.date = date;
        this.cost = cost;
    }
}
