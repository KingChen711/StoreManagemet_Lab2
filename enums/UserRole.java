package enums;

public enum UserRole {
    ADMIN(0),
    USER(1);

    private final int role;

    UserRole(int role) {
        this.role = role;
    }

    public int intValue() {
        return this.role;
    }

    public boolean checkPermission(MenuItem option) {
        return this.intValue() <= option.getRole().intValue();
    }
}
