package com.yody.common.core.dto;

import com.yody.common.enums.ResponseStatus;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

@Data
public class Result implements Serializable {

  private int code;
  private String message;
  private Object data;
  private Timestamp response_time;
  private List<String> errors;

  public Result( int code , String message, Object data){
      this.code = code;
      this.message = message;
      this.data = data;
  }

  public Result(){

  }
}
