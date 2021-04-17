package com.yody.common.thirdparty.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yody.common.core.dto.Request;
import com.yody.common.enums.HeaderEnum;
import com.yody.common.http.AbstractHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AccountServiceClient extends AbstractHttpClient {

    @Value("${yody.common.thirdparty.services.account.base-url}")
    private String url;

    public AccountServiceClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        super(restTemplate, objectMapper);
    }

    @Override
    protected HttpHeaders buildHeaders(Request request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (request.getUserId() != null)
            httpHeaders.set(HeaderEnum.HEADER_USER_ID.getValue(), request.getUserId());

        if (request.getUserName() != null)
            httpHeaders.set(HeaderEnum.HEADER_USER_NAME.getValue(), request.getUserName());

        if (request.getRequestId() != null)
            httpHeaders.set(HeaderEnum.HEADER_REQUEST_ID.getValue(), request.getRequestId());

        if (request.getApiKey() != null) {
            httpHeaders.set(HeaderEnum.X_API_KEY.getValue(), request.getApiKey());
            request.setApiKey(null);
        }

        if (request.getApiSecretKey() != null) {
            httpHeaders.set(HeaderEnum.X_API_SECRET_KEY.getValue(), request.getApiSecretKey());
            request.setApiSecretKey(null);
        }

        return httpHeaders;
    }

    @Override
    protected String baseUrl() {
        return url;
    }

}
