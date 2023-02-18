package managements;

import BuilderPattern.Customer.CustomerBuilder;
import enums.MenuItem;
import models.Customer;
import utils.Menu;
import utils.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public final class CustomerManagement {

    private static CustomerManagement instance;
    private List<Customer> customerList;
    public final Predicate<String> checkUniqueId = checkedId -> customerList.stream()
            .noneMatch(customer -> customer.getId().equals(checkedId));

    private CustomerManagement() {

    }

    public static CustomerManagement getInstance() {
        if (instance == null) {
            instance = new CustomerManagement();
        }
        return instance;
    }

    public void listAllCustomers() {
        customerList.forEach(product -> System.out.println(product.toString()));
    }

    public void searchById() {
        String searchId = Util.inputString("customer's id");
        Customer foundCustomer = findById(searchId);

        if (foundCustomer == null) {
            System.out.println("\nFail: This customer does not exist\n");
        } else {
            System.out.println(foundCustomer);
        }
    }

    public void addCustomer() {

        if (UserManagement.getInstance().isUser()) {
            return;
        }

        while (true) {
            Customer newCustomer = new Customer();
            newCustomer.input();
            customerList.add(newCustomer);
            System.out.println("\nCreate new customer successfully!\n");

            System.out.println("Do you want to add one more customer?");
            if (Menu.getConfirm() == MenuItem.CONFIRM_NO) {
                return;
            }
        }
    }

    public void updateCustomer() {

        if (UserManagement.getInstance().isUser()) {
            return;
        }

        String searchedId = Util.inputString("Customer's id");
        Customer foundCustomer = findById(searchedId);

        if (foundCustomer == null) {
            System.out.println("\nFail: Customer’s id does not exist\n");
            return;
        }

        foundCustomer.update();
        System.out.println("\nSuccess: Update customer successfully!\n");
    }

    public void saveCustomerList() {

        if (UserManagement.getInstance().isUser()) {
            return;
        }
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/customers.txt");
        try (
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw)) {

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
        Customer foundCustomer = null;
        for (Customer customer : customerList) {
            if (customer.getId().equals(id)) {
                foundCustomer = customer;
                break;
            }
        }
        return foundCustomer;
    }

    public HashMap<String, String> getMapIdToName() {
        HashMap<String, String> result = new HashMap<>();
        customerList.forEach(customer -> result.put(customer.getId(), customer.getName()));
        return result;
    }

    public void readCustomerList() {
        List<Customer> customers = new ArrayList<>();
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/customers.txt");
        try (
                FileReader fr = new FileReader(file);
                BufferedReader reader = new BufferedReader(fr)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] texts = line.split(",");
                Customer newCustomer = new CustomerBuilder()
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
