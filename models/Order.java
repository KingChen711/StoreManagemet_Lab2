package models;

import managements.CustomerManagement;
import managements.OrderManagement;
import utils.Menu;
import utils.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

    private String id;
    private String customerId;
    private String productId;
    private int quantity;
    private Date date;
    private boolean status;

    public Order(String id, String customerId, String productId, int quantity, Date date, boolean status) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
        this.status = status;
    }

    public Order() {
    }

    public String getId() {
        return id;
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
        customerId = CustomerManagement.getInstance().inputCustomerId();
        productId = Menu.getChoiceProductId();
        quantity = Util.inputPositiveInteger("order's quantity");
        date = Util.inputDate("order's date");
        status = Util.inputBoolean("order's status");
    }

    public void update() {
        status = Util.inputBooleanUpdate("order's status", status);
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return id + "," + customerId + "," + productId + "," + quantity
                + "," + dateFormat.format(date) + "," + status;
    }

}
