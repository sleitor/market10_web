package main.models.pojo;

public class OrderProduct {

    private Long uuid;
    private long uuid_order;
    private long uuid_product;
    private String name_product;
    private int count;
    private float cost;

    public OrderProduct() {

    }

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

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public long getUuid_order() {
        return uuid_order;
    }

    public void setUuid_order(long uuid_order) {
        this.uuid_order = uuid_order;
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

    public void setUuid_product(long uuid_product) {
        this.uuid_product = uuid_product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
