package managements;

import BuilderPattern.Order.OrderBuilder;
import enums.MenuItem;
import models.Order;
import utils.Menu;
import utils.Util;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public final class OrderManagement {

    private static OrderManagement instance;
    private List<Order> orderList;
    public final Predicate<String> checkUniqueId = checkedId -> orderList.stream()
            .noneMatch(order -> order.getId().equals(checkedId));

    private OrderManagement() {

    }

    public static OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }

    public void listAllOrders() {
        orderList.sort(new CustomerNameComparator());
        orderList.forEach(order -> System.out.println(order.toString()));

    }

    public void listAllPendingOrders() {
        orderList.forEach(order -> {
            if (!order.getStatus()) {
                System.out.println(order);
            }
        });

    }

    public void addOrder() {

        if (UserManagement.getInstance().isUser()) {
            return;
        }

        while (true) {
            Order newOrder = new Order();
            newOrder.input();
            orderList.add(newOrder);
            System.out.println("\nCreate new order successfully!\n");

            System.out.println("Do you want to add one more order?");
            if (Menu.getConfirm() == MenuItem.CONFIRM_NO) {
                return;
            }
        }
    }

    public void updateOrder() {

        if (UserManagement.getInstance().isUser()) {
            return;
        }

        MenuItem optionUpdate = Menu.getUpdateOrderOption();
        String searchedId = Util.inputString("order's id");
        Order foundOrder = findById(searchedId);

        if (foundOrder == null) {
            System.out.println("\nFail: Customer’s id does not exist\n");
            return;
        }

        boolean checkError = false;
        if (optionUpdate == MenuItem.ORDER_UPDATE_DELETE) {
            if (Menu.getConfirm() == MenuItem.CONFIRM_YES) {
                orderList.remove(foundOrder);
                System.out.println("\nSuccess: Delete order successfully!\n");
            } else if (Menu.getConfirm() == MenuItem.CONFIRM_NO) {
                System.out.println("\nFail: Delete order fail!\n");
                System.out.println("\nYou have refuse to delete order\n");
            } else {
                checkError = true;
            }
        } else if (optionUpdate == MenuItem.ORDER_UPDATE_INFORMATION) {
            foundOrder.update();
            System.out.println("\nSuccess: Update order information successfully!\n");
        } else {
            checkError = true;
        }

        if (checkError) {
            System.out.println("\nError: Update order failed!\n");
        }

    }

    public void saveOrderList() {

        if (UserManagement.getInstance().isUser()) {
            return;
        }
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/orders.txt");
        try (
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw)) {

            for (Order order : orderList) {
                bw.write(order.toString());
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("\nFail: Save Order list fail!\n");
            System.out.println(e.getMessage());
        }

        System.out.println("\nSuccess: Save Order list successfully!\n");

    }

    public Order findById(String id) {
        Order foundOrder = null;
        for (Order order : orderList) {
            if (order.getId().equals(id)) {
                foundOrder = order;
                break;
            }
        }
        return foundOrder;
    }

    public void readOrderList() {
        List<Order> orders = new ArrayList<>();
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/orders.txt");
        try (
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] texts = line.split(",");
                Order newOrder = new OrderBuilder()
                        .addId(texts[0])
                        .addCustomerId(texts[1])
                        .addProductId(texts[2])
                        .addQuantity(Integer.parseInt(texts[3]))
                        .addDate((texts[4]))
                        .addStatus(Boolean.parseBoolean(texts[5]))
                        .build();

                orders.add(newOrder);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        orderList = orders;
    }

    private static class CustomerNameComparator implements Comparator<Order> {
        @Override
        public int compare(Order order1, Order order2) {
            HashMap<String, String> customerIdToName = CustomerManagement.getInstance().getMapIdToName();
            String customerName1 = customerIdToName.get(order1.getCustomerId());
            String customerName2 = customerIdToName.get(order2.getCustomerId());
            return customerName1.compareTo(customerName2);
        }
    }

}
