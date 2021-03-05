package com.yody.common.core.dto;

import com.yody.common.enums.ResponseStatus;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

@Data
public class Result implements Serializable {

  private ResponseStatus status;
  private int code;
  private String message;
  private Object data;
  private String transactionId;
  private Timestamp responseTime;
  private List<String> errors;

  public Result(ResponseStatus status, int code , String message, Object data){
      this.status = status;
      this.code = code;
      this.message = message;
      this.data = data;
  }

  public Result(){

  }
}
