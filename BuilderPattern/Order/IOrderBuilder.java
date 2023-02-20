package BuilderPattern.Order;


import models.Order;

import java.util.Date;

public interface IOrderBuilder {
    OrderBuilder addId(String id);

    OrderBuilder addCustomerId(String customerId);

    OrderBuilder addProductId(String productId);

    OrderBuilder addQuantity(int quantity);

    OrderBuilder addDate(Date date);

    OrderBuilder addStatus(boolean status);

    Order build();
}
