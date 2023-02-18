package BuilderPattern.Customer;

import models.Customer;

public class CustomerBuilder implements ICustomerBuilder {
    public String id;

    public String name;
    public String address;
    public String phone;

    @Override
    public CustomerBuilder addId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public CustomerBuilder addName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public CustomerBuilder addPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public CustomerBuilder addAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public Customer build() {
        return new Customer(id, name, phone, address);
    }
}
