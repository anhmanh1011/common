package com.yody.common.filter.thirdparty.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.core.dto.Request;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
import com.yody.common.http.AbstractHttpClient;
import com.yody.common.utility.BasicAuthorization;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@Order(1)
public class AuthServiceClient extends AbstractHttpClient {

  @Value("${yody.common.thirdparty.services.auth.base-url}")
  private String url;

  public AuthServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
    super(restTemplate, objectMapper);
  }

  @Override
  protected HttpHeaders buildHeaders(Request request) {
    return defaultHeader(request);
  }

  @Override
  protected HttpHeaders buildHeaders() {
    return null;
  }

  @Override
  protected String baseUrl() {
    return url;
  }

}
