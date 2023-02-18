package BuilderPattern.Order;


import models.Order;

public interface IOrderBuilder {
    OrderBuilder addId(String id);

    OrderBuilder addCustomerId(String customerId);

    OrderBuilder addProductId(String productId);

    OrderBuilder addQuantity(int quantity);

    OrderBuilder addDate(String date);

    OrderBuilder addStatus(boolean status);

    Order build();
}
