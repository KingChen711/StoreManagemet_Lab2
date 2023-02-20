package enums;

public enum MenuItem {
    BACK("Back", UserRole.USER),
    EXIT("Exit", UserRole.USER),

    CONFIRM("Confirm", UserRole.USER),
    CONFIRM_YES("Yes", UserRole.USER),
    CONFIRM_NO("No", UserRole.USER),

    PRODUCT("Products management", UserRole.USER),
    PRODUCT_LIST_ALL("List all Products", UserRole.USER),

    CUSTOMER("Customers management", UserRole.USER),
    CUSTOMER_LIST_ALL("List all Customers", UserRole.USER),
    CUSTOMER_SEARCH_BY_ID("Search a Customer based on his/her ID", UserRole.USER),
    CUSTOMER_ADD_NEW("Add a Customer", UserRole.ADMIN),
    CUSTOMER_UPDATE("Update a Customer", UserRole.ADMIN),
    CUSTOMER_SAVE_TO_FILE("Save Customers to the file, named customers.txt", UserRole.ADMIN),

    ORDER("Orders management", UserRole.USER),
    ORDER_LIST_ALL("List all Orders in ascending order of Customer name", UserRole.USER),
    ORDER_LIST_ALL_PENDING("List all pending Orders", UserRole.USER),
    ORDER_ADD_NEW("Add new Order", UserRole.ADMIN),
    ORDER_UPDATE("Update an Order", UserRole.ADMIN),
    ORDER_UPDATE_INFORMATION("Update Order's Information", UserRole.ADMIN),
    ORDER_UPDATE_DELETE("Delete Order", UserRole.ADMIN),
    ORDER_SAVE_TO_FILE("Save Customers to the file, named customers.txt", UserRole.ADMIN),

    CHOOSING_PRODUCTS("Choose a Product", UserRole.USER);

    private final String label;
    private final UserRole role;

    MenuItem(String label, UserRole role) {
        this.label = label;
        this.role = role;
    }

    public String getLabel() {
        return label;
    }

    public UserRole getRole() {
        return role;
    }

}
