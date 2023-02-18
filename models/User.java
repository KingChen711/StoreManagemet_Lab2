package models;

import enums.UserRole;

public record User(String id, String password, UserRole role) {
}
