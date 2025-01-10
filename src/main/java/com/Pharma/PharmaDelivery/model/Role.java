package com.Pharma.PharmaDelivery.model;

public enum Role {
    ADMIN("admin"),
    USER("user");

    private String role;

    Role(String role){
        this.role = role;
    }
}
