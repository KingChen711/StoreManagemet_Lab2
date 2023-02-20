package managements;

import BuilderPattern.Order.OrderBuilder;
import enums.MenuItem;
import models.Order;
import utils.Menu;
import utils.Util;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public final class OrderManagement {

    private static OrderManagement instance;
    private List<Order> orderList;
    public final Predicate<String> checkUniqueId =
            checkedId -> orderList
                    .stream()
                    .noneMatch(order -> order.getId().equals(checkedId));
    Runnable addAnOrder = () -> {
        Order newOrder = new Order();
        newOrder.input();
        orderList.add(newOrder);
        System.out.println("\nCreate new order successfully!\n");
    };
    Runnable updateAnOrder = () -> {
        String searchedId = Util.inputString("order's id");
        Order foundOrder = findById(searchedId);

        if (foundOrder == null) {
            System.out.println("\nFail: Customer’s id does not exist\n");
            return;
        }

        switch (Menu.getUpdateOrderOption()) {
            case ORDER_UPDATE_DELETE -> {
                if (Menu.getConfirm() == MenuItem.CONFIRM_YES) {
                    orderList.remove(foundOrder);
                    System.out.println("\nSuccess: Delete order successfully!\n");
                } else {
                    System.out.println("\nFail: Delete order fail!");
                    System.out.println("You have refuse to delete order\n");
                }
            }
            case ORDER_UPDATE_INFORMATION -> {
                foundOrder.update();
                System.out.println("\nSuccess: Update order information successfully!\n");
            }
            default -> System.out.println("\nError: Update order failed!\n");
        }
    };

    private OrderManagement() {

    }

    public static OrderManagement getInstance() {
        if (instance == null) {
            instance = new OrderManagement();
        }
        return instance;
    }

    public void listAllOrders() {
        orderList
                .stream()
                .sorted(new CustomerNameComparator())
                .forEach(System.out::println);

    }

    public void listAllPendingOrders() {
        orderList.forEach(order -> {
            if (!order.getStatus()) {
                System.out.println(order);
            }
        });

    }

    public void addOrder() {
        if (UserManagement.getInstance().isUser()) return;
        Util.multiUse(addAnOrder, "Do you want to add more orders?");
    }

    public void updateOrder() {
        if (UserManagement.getInstance().isUser()) return;
        Util.multiUse(updateAnOrder, "Do you want to update more orders?");
    }

    public void saveOrderList() {

        if (UserManagement.getInstance().isUser()) return;

        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/orders.txt");
        try (FileWriter fw = new FileWriter(file); BufferedWriter bw = new BufferedWriter(fw)) {

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
        return orderList
                .stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public void readOrderList() {
        orderList = new ArrayList<>();
        String currentWorkingDirectory = System.getProperty("user.dir");
        File file = new File(currentWorkingDirectory + "/src/resources/orders.txt");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] texts = line.split(",");
                Order newOrder =
                        new OrderBuilder()
                                .addId(texts[0])
                                .addCustomerId(texts[1])
                                .addProductId(texts[2])
                                .addQuantity(Integer.parseInt(texts[3]))
                                .addDate(dateFormat.parse(texts[4]))
                                .addStatus(Boolean.parseBoolean(texts[5]))
                                .build();

                orderList.add(newOrder);
            }
        } catch (IOException | NumberFormatException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static class CustomerNameComparator implements Comparator<Order> {
        private final Map<String, String> customerIdToName = CustomerManagement.getInstance().getMapIdToName();

        @Override
        public int compare(Order order1, Order order2) {
            return customerIdToName.get(order1.getCustomerId()).compareTo(customerIdToName.get(order2.getCustomerId()));
        }
    }
}
