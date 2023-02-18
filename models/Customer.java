package models;

import managements.CustomerManagement;
import utils.Util;

public class Customer {

    private String id;
    private String name;
    private String address;
    private String phone;

    public Customer(String id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void input() {
        id = Util.inputIdWithFormat("customer's id", "not empty,unique,format: \"Cxxx\"",
                CustomerManagement.getInstance().checkUniqueId, "C[0-9]{3}");
        name = Util.inputString("customer's name");
        address = Util.inputString("customer's address");
        phone = Util.inputStringLength("customer's phone", 10, 12);
    }

    public void update() {
        name = Util.inputStringUpdate("customer's name", name);
        address = Util.inputStringUpdate("customer's address", address);
        phone = Util.inputStringLengthUpdate("customer's phone", 10, 12, phone);
    }

    @Override
    public String toString() {
        return id + "," + name + "," + address + "," + phone;
    }

}
