package utils;

import enums.MenuItem;
import managements.CustomerManagement;
import managements.ProductManagement;
import managements.UserManagement;
import models.User;

import java.util.List;

public final class Menu {

    private static final MenuItem[] primaryOptions = {
            MenuItem.EXIT,
            MenuItem.PRODUCT,
            MenuItem.CUSTOMER,
            MenuItem.ORDER,
    };

    private static final MenuItem[] productsOptions = {
            MenuItem.BACK,
            MenuItem.PRODUCT_LIST_ALL
    };

    private static final MenuItem[] customersOptions = {
            MenuItem.BACK,
            MenuItem.CUSTOMER_LIST_ALL,
            MenuItem.CUSTOMER_SEARCH_BY_ID,
            MenuItem.CUSTOMER_ADD_NEW,
            MenuItem.CUSTOMER_UPDATE,
            MenuItem.CUSTOMER_SAVE_TO_FILE,
    };

    private static final MenuItem[] ordersOptions = {
            MenuItem.BACK,
            MenuItem.ORDER_LIST_ALL,
            MenuItem.ORDER_LIST_ALL_PENDING,
            MenuItem.ORDER_ADD_NEW,
            MenuItem.ORDER_UPDATE,
            MenuItem.ORDER_SAVE_TO_FILE,
    };

    private static final MenuItem[] orderUpdateOptions = {
            MenuItem.ORDER_UPDATE_DELETE,
            MenuItem.ORDER_UPDATE_INFORMATION,
    };

    private static final MenuItem[] confirmOptions = {
            MenuItem.CONFIRM_NO,
            MenuItem.CONFIRM_YES,
    };

    private MenuItem primaryOption;
    private MenuItem subOption;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public static MenuItem getConfirm() {
        return getChoice(MenuItem.CONFIRM);
    }

    public static MenuItem getUpdateOrderOption() {
        return getChoice(MenuItem.ORDER_UPDATE);
    }

    private static MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Store management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputIntegerWithRange("Please enter your choice", 0, numItems - 1);
        return optionList[choice];
    }

    private static String getChoiceId(List<String> Ids, String menuCaption) {
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        for (int i = 0; i < Ids.size(); i++) {
            System.out.printf("(%d) -> %s%n", i, Ids.get(i));
        }
        System.out.println("*********************************************");

        int choice = Util.inputIntegerWithRange("Please enter your choice", 0, Ids.size() - 1);
        String chosenId = Ids.get(choice);
        System.out.println("You have chosen " + chosenId);
        return chosenId;
    }

    private static int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        User currentUser = UserManagement.getInstance().getCurrentUser();
        for (int i = 1; i < optionList.length; i++) {
            if (currentUser.role().checkPermission(optionList[i])) {
                System.out.printf("(%d) -> %s%n", numItems, optionList[i].getLabel());
                numItems++;
            }
        }
        System.out.printf("(0) -> %s%n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private static MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case PRODUCT -> productsOptions;
                case CUSTOMER -> customersOptions;
                case ORDER -> ordersOptions;
                case CONFIRM -> confirmOptions;
                case ORDER_UPDATE -> orderUpdateOptions;
                default -> primaryOptions;
            };
        }
        return optionList;
    }

    public static String getChoiceCustomerId() {
        return getChoiceId(CustomerManagement.getInstance().getCustomerIds(), "Choose a customer");
    }

    public static String getChoiceProductId() {
        return getChoiceId(ProductManagement.getInstance().getProductIds(), "Choose a product");
    }

    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }
}
