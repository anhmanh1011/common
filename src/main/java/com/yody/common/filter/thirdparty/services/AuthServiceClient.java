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
        HttpHeaders httpHeaders = new HttpHeaders();
        if (request.getUserId() != null)
            httpHeaders.set(FieldConstant.OPERATOR_KC_ID, request.getUserId());

        if (request.getUserName() != null)
            httpHeaders.set(FieldConstant.OPERATOR_LOGIN_ID, request.getUserName());

        if (request.getRequestId() != null)
            httpHeaders.set(HeaderEnum.HEADER_REQUEST_ID.getValue(), request.getRequestId());

        if (request.getBasicUserName() != null && !"".equals(request.getBasicUserName())
            && null != request.getBasicPassword() && !"".equals(request.getBasicPassword())) {
            httpHeaders.set(HeaderEnum.HEADER_AUTHORIZATION.getValue(),
                BasicAuthorization.encodeBasicAuthorization(request.getBasicUserName(), request.getBasicPassword()));
            request.setBasicPassword(null);
            request.setBasicUserName(null);
        }
        return httpHeaders;
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
