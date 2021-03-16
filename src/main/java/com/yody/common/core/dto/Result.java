package com.yody.common.core.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.yody.common.enums.ResponseStatus;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import org.json.JSONPropertyName;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Result implements Serializable {

  private int code;
  private String message;
  private Object data;
  private Timestamp responseTime;
  private List<String> errors;

  public Result( int code , String message, Object data){
      this.code = code;
      this.message = message;
      this.data = data;
  }

  public Result(){

  }
}
