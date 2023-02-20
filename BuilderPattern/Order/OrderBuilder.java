package BuilderPattern.Order;

import models.Order;

import java.util.Date;

public class OrderBuilder implements IOrderBuilder {
    public String id;
    public String customerId;
    public String productId;
    public int quantity;
    public Date date;
    public boolean status;

    @Override
    public OrderBuilder addId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public OrderBuilder addCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    @Override
    public OrderBuilder addProductId(String productId) {
        this.productId = productId;
        return this;
    }

    @Override
    public OrderBuilder addQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public OrderBuilder addDate(Date date) {
        this.date = date;
        return this;
    }

    @Override
    public OrderBuilder addStatus(boolean status) {
        this.status = status;
        return this;
    }

    @Override
    public Order build() {
        return new Order(id, customerId, productId, quantity, date, status);
    }
}
