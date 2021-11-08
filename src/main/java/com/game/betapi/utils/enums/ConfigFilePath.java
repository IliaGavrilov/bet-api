package com.game.betapi.utils.enums;

public enum ConfigFilePath {
    STATUS_CODES("status_msg");

    private String value;

    ConfigFilePath(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
