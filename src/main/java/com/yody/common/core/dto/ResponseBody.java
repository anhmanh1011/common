package com.yody.common.core.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class ResponseBody<T> {

  private String code;
  private String message;
  private T data;
  private String transactionId;
  private Timestamp responseTime = new Timestamp(System.currentTimeMillis());
}
