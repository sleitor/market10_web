package main.models.pojo;

public class Product {

    private Long uuid;
    private String name;
    private String description;
    private int quantity;
    private float cost;

    public Product(long uuid, String name, String description, int quantity, float cost) {

        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.cost = cost;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof Product && uuid == ((Product) obj).uuid;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
