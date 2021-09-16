package com.yody.common.filter.thirdparty.authen.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.core.dto.Request;
import com.yody.common.core.dto.Result;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
import com.yody.common.filter.thirdparty.authen.request.GetUserInfoRequest;
import com.yody.common.filter.thirdparty.authen.response.GetUserInfoResponse;
import com.yody.common.http.AbstractHttpClient;
import com.yody.common.utility.BasicAuthorization;
import com.yody.common.utility.Strings;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AuthenServiceClient {

  @Value("${yody.core.3rd.authen.base-url}")
  private String url;
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  @Autowired
  public AuthenServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  public GetUserInfoResponse get(final GetUserInfoRequest request, final ParameterizedTypeReference<GetUserInfoResponse> dataClass) {
    this.logRequest(HttpMethod.GET, request);
    ResponseEntity<GetUserInfoResponse> responseEntity = restTemplate
        .exchange(this.baseUrl().concat(request.getPath()), HttpMethod.GET, new HttpEntity<>(this.buildHeaders(request)), dataClass);
    this.logResponse(responseEntity.getBody(), request.getPath());
    return responseEntity.getBody();
  }

  private void logRequest(HttpMethod method, Request request) {
    try {
      log.debug("Request {} to endpoint {} with data: {}", method, this.baseUrl() + request.getPath(), objectMapper.writeValueAsString(request));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  public <T> void logResponse(T responseBody, String requestUri) {
    try {
      log.debug("Response from {}: {}", this.baseUrl() + requestUri, objectMapper.writeValueAsString(responseBody));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private HttpHeaders buildHeaders(GetUserInfoRequest request) {
    HttpHeaders headers = new HttpHeaders();
    headers.put("Authorization", Collections.singletonList(request.getToken()));
    return headers;
  }

  private String baseUrl() {
    return url;
  }
}
