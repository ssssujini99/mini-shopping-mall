package com.programmers.zigzag.user.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum UserRole {

    CUSTOMER("customer"),
    SELLER("seller");

    private final String typeValue;

    UserRole(String typeValue) {
        this.typeValue = typeValue;
    }

    public static UserRole getType(String value) {
        return Arrays.stream(UserRole.values())
                .filter(type -> type.typeValue.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
