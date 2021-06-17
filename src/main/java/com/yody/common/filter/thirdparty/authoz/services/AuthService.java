package com.yody.common.filter.thirdparty.authoz.services;

import com.yody.common.core.dto.Result;
import com.yody.common.filter.thirdparty.authoz.request.PermissionRequestDto;
import com.yody.common.filter.thirdparty.authoz.response.PermissionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Order(2)
public class AuthService {
    private final AuthServiceClient authServiceClient;

    @Value("${yody.common.thirdparty.services.auth.username}")
    private String username;

    @Value("${yody.common.thirdparty.services.auth.password}")
    private String password;

    public AuthService(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    public Result<PermissionResponseDto> getPermissionInfo(PermissionRequestDto request) {
        request.setBasicUserName(username);
        request.setBasicPassword(password);
        ParameterizedTypeReference<Result<PermissionResponseDto>> parameterizedTypeReference
            = new ParameterizedTypeReference<Result<PermissionResponseDto>>() {
        };
        Result<PermissionResponseDto> result = authServiceClient.get(request, parameterizedTypeReference);
        return result;
    }
}
