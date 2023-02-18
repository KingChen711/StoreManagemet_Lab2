package BuilderPattern.Customer;

import models.Customer;

public interface ICustomerBuilder {
    CustomerBuilder addId(String id);

    CustomerBuilder addName(String name);

    CustomerBuilder addPhone(String phone);

    CustomerBuilder addAddress(String address);

    Customer build();
}
