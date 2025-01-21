package com.neoris.customer.common.enums;

public enum IsActive {
    INACTIVE("0"), ACTIVE("1");

    public final String value;

    IsActive(String value) {
        this.value = value;
    }
}
