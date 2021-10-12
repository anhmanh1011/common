package com.yody.common.core;

import lombok.Data;

@Data
public class RequestInfo {
  private String operatorKcId = "";
  private String operatorLoginId = "";
  private String requestId = "";
  private String authorization = "";
  private String token = "";
  private String operatorName = "";
  private String fullName = "";
  private String code = "";
  private boolean isBasicAuth = false;
}
