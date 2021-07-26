package com.yody.common.filter.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BasicRequest {
  protected String createdBy;
  protected String createdName;
  protected String updatedBy;
  protected String updatedName;
  protected String requestId;


}
