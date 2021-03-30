package com.yody.common.http.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.http.request.AbstractHttpRequest;
import com.yody.common.http.response.BaseResponse;
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
    }

    public <T> BaseResponse<T> post(final AbstractHttpRequest request, final ParameterizedTypeReference<BaseResponse<T>> dataClass) {
        this.logRequest(HttpMethod.POST, request);
        ResponseEntity<BaseResponse<T>> responseEntity = restTemplate.exchange(
                this.baseUrl().concat(request.getPath()),
                HttpMethod.POST,
                new HttpEntity<>(request, this.buildHeaders()),
                dataClass
        );
        this.logResponse(responseEntity.getBody(), request.getPath());
        return responseEntity.getBody();
    }

    public <T> BaseResponse<T> put(final AbstractHttpRequest request, final ParameterizedTypeReference<BaseResponse<T>> dataClass) {
        this.logRequest(HttpMethod.PUT, request);
        ResponseEntity<BaseResponse<T>> responseEntity = restTemplate.exchange(
                this.baseUrl().concat(request.getPath()),
                HttpMethod.PUT,
                new HttpEntity<>(request, this.buildHeaders()),
                dataClass
        );
        this.logResponse(responseEntity.getBody(), request.getPath());
        return responseEntity.getBody();
    }

    public <T> BaseResponse<T> get(final AbstractHttpRequest request, final ParameterizedTypeReference<BaseResponse<T>> dataClass) {
        this.logRequest(HttpMethod.GET, request);
        ResponseEntity<BaseResponse<T>> responseEntity = restTemplate.exchange(
                this.baseUrl().concat(request.getPath()),
                HttpMethod.GET,
                new HttpEntity<>(this.buildHeaders()),
                dataClass
        );
        this.logResponse(responseEntity.getBody(), request.getPath());
        return responseEntity.getBody();
    }

    public final RestTemplate getRestTemplate() {
    	return this.restTemplate;
    }

    protected abstract HttpHeaders buildHeaders();

    protected abstract String baseUrl();

    private void logRequest(HttpMethod method, AbstractHttpRequest request) {
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
}
