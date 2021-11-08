package com.game.betapi.utils;

import java.util.Map;

public class StatusCodes {
    private static Map<String, String> statusValues;

    public static void setStatusValues(Map<String, String> statusValues) {
        StatusCodes.statusValues = statusValues;
    }

    public static String getValue(String resultCode) {
        try {
           return statusValues.get(resultCode);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Cannot provide value.");
        }
    }
}
