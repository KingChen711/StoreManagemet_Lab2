package BuilderPattern.Product;

import models.Product;

public class ProductBuilder implements IProductBuilder {
    private String id;
    private String name;
    private String unit;
    private String origin;
    private double price;

    @Override
    public ProductBuilder addId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public ProductBuilder addName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ProductBuilder addUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    public ProductBuilder addOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    @Override
    public ProductBuilder addPrice(double price) {
        this.price = price;
        return this;
    }

    @Override
    public Product build() {
        return new Product(id, name, unit, origin, price);
    }
}
