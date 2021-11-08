package com.game.betapi.utils.enums;

public enum StatusCode {
    SUCCESS("0"),
    OUT_OF_BOUNDS_PARAMS("1"),
    INTERNAL_SERVICE_ERROR("2");

    private String value;

    StatusCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
