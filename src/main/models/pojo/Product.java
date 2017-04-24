package main.models.pojo;

import java.util.UUID;

/**
 * Created by User on 20.04.2017.
 */
public class Product {

    private long uuid;
    private String name;
    private String description;
    private int quantity;
    private float cost;

    public long getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public Product(long uuid, String name, String description, int quantity, float cost) {

        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
    }

}
