package com.example.demo.component.enums;

public enum Autorizations {
    ALLOWD("ADMIN"),
    NOTALLOWED("USER");

    private final String value;

    Autorizations(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
