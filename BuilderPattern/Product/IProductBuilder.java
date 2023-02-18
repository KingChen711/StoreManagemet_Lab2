package BuilderPattern.Product;


import models.Product;

public interface IProductBuilder {
    ProductBuilder addId(String id);

    ProductBuilder addName(String name);

    ProductBuilder addUnit(String unit);

    ProductBuilder addOrigin(String origin);

    ProductBuilder addPrice(double price);

    Product build();
}
