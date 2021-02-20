package com.yody.common.core.dto;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiErrorDTO {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiErrorDTO(HttpStatus status, String message, String error) {
    this.status = status;
    this.message = message;
    this.errors = Arrays.asList(error);
  }

}
