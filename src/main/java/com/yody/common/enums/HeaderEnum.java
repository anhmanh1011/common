package com.yody.common.enums;

public enum HeaderEnum {
    HEADER_USER_ID("user_id"),
    HEADER_USER_NAME("user_name"),
    HEADER_REQUEST_ID("request_id"),
    HEADER_AUTHORIZATION("Authorization"),
    BASIC_USERNAME("BASIC-USERNAME"),
    BASIC_PASSWORD("BASIC-PASSWORD");

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
