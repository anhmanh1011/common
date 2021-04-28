package com.yody.common.core.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.yody.common.enums.BaseEnum;
import com.yody.common.enums.CommonResponseCode;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class Result<T> implements Serializable {

    private int code;
    private String message;
    private T data;
    private Long responseTime;
    private List<String> errors;
    private String requestId;

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(BaseEnum<Integer> enums, T data) {
        this.code = enums.getValue();
        this.message = enums.getDisplayName();
        this.data = data;
        this.responseTime = System.currentTimeMillis();
    }

    public Result(int code, String message, T data, String requestId) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.requestId = requestId;
    }

    public Result(int code, String message, String requestId) {
        this.code = code;
        this.message = message;
        this.requestId = requestId;
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(CommonResponseCode.SUCCESS, data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(CommonResponseCode.SUCCESS.getValue(), message, data);
    }

    public static <T> Result<T> error(String requestId, int errorCode, String message) {
        return new Result<>(errorCode, message, requestId);
    }

    public Result() {
    }
}
