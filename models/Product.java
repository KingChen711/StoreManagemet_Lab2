package models;

public class Product {
    private String id;
    private String name;
    private String unit;
    private String origin;
    private double price;

    public Product(String id, String name, String unit, String origin, double price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.origin = origin;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + unit + "," + origin + "," + price;
    }
}
