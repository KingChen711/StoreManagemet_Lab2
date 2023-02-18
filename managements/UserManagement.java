package managements;

import enums.UserRole;
import models.User;
import utils.Util;

import java.util.ArrayList;

public final class UserManagement {

    private static UserManagement instance;
    private User currentUser;

    private UserManagement() {

    }

    public static UserManagement getInstance() {
        if (instance == null) {
            instance = new UserManagement();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void login() {
        System.out.println("==============Login==============");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User("admin", "123", UserRole.ADMIN));
        userList.add(new User("user", "123", UserRole.USER));

        while (true) {
            String username = Util.inputString("user's id");
            String password = Util.inputString("password");

            for (User user : userList) {
                if (username.equals(user.id()) && password.equals(user.password())) {
                    System.out.println("\nSuccess: login successful\n");
                    currentUser = user;
                    return;
                }
            }

            System.out.println("\nFail: login fail");
            System.out.println("User's ID or Password is incorrect\n");
        }
    }

    public boolean isUser() {
        if (currentUser.role().equals(UserRole.USER)) {
            System.out.println("\nThis feature is only used by admin!\n");
            return true;
        }
        return false;
    }

}
