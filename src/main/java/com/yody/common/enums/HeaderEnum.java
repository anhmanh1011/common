package com.yody.common.enums;

public enum HeaderEnum {
    HEADER_USER_ID("user_id"),
    HEADER_USER_NAME("user_name"),
    HEADER_REQUEST_ID("request_id"),
    X_API_KEY("X-API-KEY"),
    X_API_SECRET_KEY("X-API-SECRET");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    HeaderEnum(String value) {
        this.value = value;
    }
}
