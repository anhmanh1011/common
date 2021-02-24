package com.yody.common.core.dto;

import com.yody.common.enums.ResponseStatus;
import java.sql.Timestamp;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

@Data
public class Result {

  private ResponseStatus status;
  private String message;
  private Object data;
  private String transactionId;
  private Timestamp responseTime;
}
