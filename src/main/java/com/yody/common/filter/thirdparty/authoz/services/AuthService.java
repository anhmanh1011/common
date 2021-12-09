package com.yody.common.filter.thirdparty.authoz.services;

import com.yody.common.core.dto.PermissionResponseDto;
import com.yody.common.core.dto.Result;
import com.yody.common.filter.thirdparty.authoz.request.PermissionRequestDto;
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

    @Value("${yody.core.3rd.auth.basic.username}")
    private String username;

    @Value("${yody.core.3rd.auth.basic.password}")
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
        return authServiceClient.get(request, parameterizedTypeReference);
    }
}
