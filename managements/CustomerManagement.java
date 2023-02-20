package managements;

import BuilderPattern.Customer.CustomerBuilder;
import models.Customer;
import utils.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class CustomerManagement {

    private static CustomerManagement instance;
    private List<Customer> customerList;

    public final Predicate<String> checkUniqueId =
            checkedId -> customerList
                    .stream()
                    .noneMatch(customer -> customer.getId().equals(checkedId));
    Runnable addACustomer = () -> {
        Customer newCustomer = new Customer();
        newCustomer.input();
        customerList.add(newCustomer);
        System.out.println("\nCreate new customer successfully!\n");
    };

    Runnable updateACustomer = () -> {
        String searchedId = Util.inputString("Customer's id");
        Customer foundCustomer = findById(searchedId);

        if (foundCustomer == null) {
            System.out.println("\nFail: Customer’s id does not exist\n");
            return;
        }

        foundCustomer.update();
        System.out.println("\nSuccess: Update customer successfully!\n");
    };

    private CustomerManagement() {

    }

    public static CustomerManagement getInstance() {
        if (instance == null) {
            instance = new CustomerManagement();
        }
        return instance;
    }

    public String inputCustomerId() {
        String customerId = Util.inputWithFormat("customer's id", "not empty,format: \"Cxxx\"", "C[0-9]{3}");

        if (!checkExistId(customerId)) {
            System.out.println("\nThis customer's id does not exist in database! Please give more information about this customer");
            Customer newCustomer = new Customer();
            newCustomer.input(customerId);
            customerList.add(newCustomer);
            System.out.println("\nCreate new customer successfully!\n");
        }

        return customerId;
    }

    public boolean checkExistId(String id) {
        return customerList.stream().anyMatch(customer -> customer.getId().equals(id));
    }

    public List<String> getCustomerIds() {
        return customerList
                .stream()
                .map(Customer::getId)
                .collect(Collectors.toList());
    }

    public void listAllCustomers() {
        customerList.forEach(product -> System.out.println(product.toString()));
    }

    public void searchById() {
        String searchedId = Util.inputString("customer's id");
        Customer foundCustomer = findById(searchedId);

        if (foundCustomer == null) {
            System.out.println("\nFail: This customer does not exist\n");
        } else {
            System.out.println(foundCustomer);
        }
    }

    public void addCustomer() {
        if (UserManagement.getInstance().isUser()) return;
        Util.multiUse(addACustomer, "Do you want to add more customers?");
    }


    public void updateCustomer() {
        if (UserManagement.getInstance().isUser()) return;
        Util.multiUse(updateACustomer, "Do you want to update more customers?");
    }

    public void saveCustomerList() {

        if (UserManagement.getInstance().isUser()) return;

        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/customers.txt");
        try (FileWriter fw = new FileWriter(file); BufferedWriter writer = new BufferedWriter(fw)) {

            for (Customer customer : customerList) {
                writer.write(customer.toString());
                writer.newLine();
            }

            System.out.println("\nSuccess: Save Customer list successfully!\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Customer findById(String id) {
        return customerList
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Map<String, String> getMapIdToName() {
        return customerList
                .stream()
                .collect(Collectors.toMap(Customer::getId, Customer::getName));
    }


    public void readCustomerList() {
        List<Customer> customers = new ArrayList<>();
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/customers.txt");
        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] texts = line.split(",");
                Customer newCustomer =
                        new CustomerBuilder()
                                .addId(texts[0])
                                .addName(texts[1])
                                .addAddress(texts[2])
                                .addPhone(texts[3])
                                .build();

                customers.add(newCustomer);
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        customerList = customers;
    }


}
