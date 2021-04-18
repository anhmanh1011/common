package com.yody.common.filter.thirdparty.services;

import com.yody.common.core.dto.Result;
import com.yody.common.filter.thirdparty.request.PermissionRequestDto;
import com.yody.common.filter.thirdparty.response.PermissionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Order(2)
public class AuthService {
    private final AuthServiceClient authServiceClient;

    public AuthService(AuthServiceClient authServiceClient) {
        this.authServiceClient = authServiceClient;
    }

    public Result<PermissionResponseDto> getPermissionInfo(PermissionRequestDto request) {
        ParameterizedTypeReference<Result<PermissionResponseDto>> parameterizedTypeReference
            = new ParameterizedTypeReference<Result<PermissionResponseDto>>() {
        };
        Result<PermissionResponseDto> result = authServiceClient.get(request, parameterizedTypeReference);
        return result;
    }
}
