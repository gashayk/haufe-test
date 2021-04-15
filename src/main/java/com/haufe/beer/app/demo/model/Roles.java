package com.haufe.beer.app.demo.model;

public enum Roles {

    ADMIN("ADMIN"), MANUFACTURER("MANUFACTURER"), ANONYMOUS("ANONYMOUS");

    private final String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
