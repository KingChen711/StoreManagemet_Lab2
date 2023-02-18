package models;

import managements.OrderManagement;
import managements.ProductManagement;
import utils.Util;

public class Order {

    private String id;
    private String customerId;
    private String productId;
    private int quantity;
    private String date;
    private boolean status;

    public Order(String id, String customerId, String productId, int quantity, String date, boolean status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
    }

    public Order() {
    }


    public String getCustomerId() {
        return customerId;
    }

    public boolean getStatus() {
        return status;
    }

    public void input() {
        id = Util.inputIdWithFormat("order's id", "not empty,unique,format: \"Dxxx\"",
                OrderManagement.getInstance().checkUniqueId, "D[0-9]{3}");
        customerId = Util.inputWithFormat("customer's id", "not empty,format: \"Cxxx\"", "C[0-9]{3}");
        productId = Util.inputExistId("product's id", "not empty,format: \"Pxxx\"", "P[0-9]{3}",
                ProductManagement.getInstance().checkExistId);
        quantity = Util.inputPositiveInteger("order's quantity");
        date = Util.inputString("order's date");
        status = Util.inputBoolean("order's status");
    }

    public void update() {
        customerId = Util.inputString("customer's id");
        productId = Util.inputString("product's id");
        quantity = Util.inputPositiveIntegerUpdate("order's quantity", quantity);
        date = Util.inputStringUpdate("order's date", date);
        status = Util.inputBooleanUpdate("order's status", status);
    }

    @Override
    public String toString() {
        return id + "," + customerId + "," + productId + "," + quantity
                + "," + date + "," + status;
    }

}
