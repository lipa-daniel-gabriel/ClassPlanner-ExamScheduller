package com.java.siit.licenta.config;

public enum UserPermission {
    USER_READ("loginEntity:read"),
    USER_WRITE("loginEntity:write"),
    CALCULUS_READ("teacher:read"),
    CALCULUS_WRITE("teacher:write");


    private String permissions;

    UserPermission(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
