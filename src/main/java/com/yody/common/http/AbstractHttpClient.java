package com.yody.common.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.core.RestTemplateErrorHandler;
import com.yody.common.core.dto.Result;
import com.yody.common.core.dto.Request;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.filter.constant.FieldConstant;
import com.yody.common.utility.BasicAuthorization;
import com.yody.common.utility.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public abstract class AbstractHttpClient {
    protected final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public AbstractHttpClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.restTemplate.setErrorHandler(new RestTemplateErrorHandler());
    }

    public <T> Result<T> post(final Request request, final ParameterizedTypeReference<Result<T>> dataClass) {
        this.logRequest(HttpMethod.POST, request);
        ResponseEntity<Result<T>> responseEntity = restTemplate.exchange(
                this.baseUrl().concat(request.getPath()),
                HttpMethod.POST,
                new HttpEntity<>(request, this.buildHeaders(request)),
                dataClass
        );
        this.logResponse(responseEntity.getBody(), request.getPath());
        return responseEntity.getBody();
    }

    public <T> Result<T> put(final Request request, final ParameterizedTypeReference<Result<T>> dataClass) {
        this.logRequest(HttpMethod.PUT, request);
        ResponseEntity<Result<T>> responseEntity = restTemplate.exchange(
                this.baseUrl().concat(request.getPath()),
                HttpMethod.PUT,
                new HttpEntity<>(request, this.buildHeaders(request)),
                dataClass
        );
        this.logResponse(responseEntity.getBody(), request.getPath());
        return responseEntity.getBody();
    }

    public <T> Result<T> get(final Request request, final ParameterizedTypeReference<Result<T>> dataClass) {
        this.logRequest(HttpMethod.GET, request);
        ResponseEntity<Result<T>> responseEntity = restTemplate.exchange(
                this.baseUrl().concat(request.getPath()),
                HttpMethod.GET,
                new HttpEntity<>(this.buildHeaders(request)),
                dataClass
        );
        this.logResponse(responseEntity.getBody(), request.getPath());
        return responseEntity.getBody();
    }

    public final RestTemplate getRestTemplate() {
    	return this.restTemplate;
    }

    protected abstract HttpHeaders buildHeaders(Request request);
    protected abstract HttpHeaders buildHeaders();

    protected abstract String baseUrl();

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

    protected  HttpHeaders defaultHeader(Request request){
        HttpHeaders httpHeaders = new HttpHeaders();
        if (request.getUserId() != null) {
            log.info("Set operator_kc_id: {}", request.getUserId());
            httpHeaders.set(FieldConstant.OPERATOR_KC_ID, request.getUserId());
        }

        if (request.getUserName() != null) {
            log.info("Set operator_login_id: {}", request.getUserName());
            httpHeaders.set(FieldConstant.OPERATOR_LOGIN_ID, request.getUserName());
        }

        if (request.getRequestId() != null) {
            log.info("Set request_id: {}", request.getRequestId());
            httpHeaders.set(HeaderEnum.HEADER_REQUEST_ID.getValue(), request.getRequestId());
        }

        if (!Strings.isEmpty(request.getBasicUserName()) && !Strings.isEmpty(request.getBasicPassword())) {
            log.info("Set basic auth: {} - {}", request.getBasicUserName(), request.getBasicPassword());
            httpHeaders.set(HeaderEnum.HEADER_AUTHORIZATION.getValue(), BasicAuthorization.encodeBasicAuthorization(request.getBasicUserName(), request.getBasicPassword()));
            request.setBasicPassword(null);
            request.setBasicUserName(null);
        }
        return httpHeaders;
    }


}
