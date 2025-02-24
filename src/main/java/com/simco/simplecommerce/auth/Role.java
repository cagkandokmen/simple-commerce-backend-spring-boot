package com.simco.simplecommerce.auth;

public enum Role {
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN");
    private final String roleName;
    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public static Role fromString(String role) {
        if (role == null || role.trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        for (Role r : Role.values()) {
            if (r.getRoleName().equals(role)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid role: " + role);
    }
}