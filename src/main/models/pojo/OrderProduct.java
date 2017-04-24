package main.models.pojo;

import java.util.UUID;

/**
 * Created by User on 20.04.2017.
 */
public class OrderProduct {

    private long uuid;
    private long uuid_order;
    private long uuid_product;
    private int count;
    private float cost;

    public long getUuid() {
        return uuid;
    }

    public long getUuid_order() {
        return uuid_order;
    }

    public long getUuid_product() {
        return uuid_product;
    }

    public int getCount() {
        return count;
    }

    public float getCost() {
        return cost;
    }

    public OrderProduct(long uuid, long uuid_order, long uuid_product, int count, float cost) {

        this.uuid = uuid;
        this.uuid_order = uuid_order;
        this.uuid_product = uuid_product;
        this.count = count;
        this.cost = cost;
    }
}
