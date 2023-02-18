package app;

import enums.MenuItem;
import managements.CustomerManagement;
import managements.OrderManagement;
import managements.ProductManagement;
import managements.UserManagement;
import utils.Menu;

public class App {
    private ProductManagement productManagement;
    private CustomerManagement customerManagement;
    private OrderManagement orderManagement;

    public static void main(String[] args) {
        new App().start();
    }

    private void prepareData() {
        productManagement.readProductList();
        customerManagement.readCustomerList();
        orderManagement.readOrderList();
    }

    private void run() {
        Menu menu = new Menu();
        MenuItem userChoice;

        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case PRODUCT_LIST_ALL -> productManagement.listAllProducts();

                case CUSTOMER_LIST_ALL -> customerManagement.listAllCustomers();
                case CUSTOMER_SEARCH_BY_ID -> customerManagement.searchById();
                case CUSTOMER_ADD_NEW -> customerManagement.addCustomer();
                case CUSTOMER_UPDATE -> customerManagement.updateCustomer();
                case CUSTOMER_SAVE_TO_FILE -> customerManagement.saveCustomerList();

                case ORDER_LIST_ALL -> orderManagement.listAllOrders();
                case ORDER_LIST_ALL_PENDING -> orderManagement.listAllPendingOrders();
                case ORDER_ADD_NEW -> orderManagement.addOrder();
                case ORDER_UPDATE -> orderManagement.updateOrder();
                case ORDER_SAVE_TO_FILE -> orderManagement.saveOrderList();

                case EXIT -> System.out.println("Exited!");

                default -> System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    private void start() {
        System.out.println("Order management");

        productManagement = ProductManagement.getInstance();
        customerManagement = CustomerManagement.getInstance();
        orderManagement = OrderManagement.getInstance();

        UserManagement.getInstance().login();

        prepareData();
        run();
    }
}
