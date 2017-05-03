package main.models.pojo;

public class OrderProduct {

    private long uuid;
    private long uuid_order;
    private long uuid_product;
    private String name_product;
    private int count;
    private float cost;

    public OrderProduct(long uuid, long uuid_order, long uuid_product, int count, float cost) {

        this.uuid = uuid;
        this.uuid_order = uuid_order;
        this.uuid_product = uuid_product;
        this.count = count;
        this.cost = cost;
    }

    public long getUuid() {
        return uuid;
    }

    public long getUuid_order() {
        return uuid_order;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
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
}
