package com.yody.common.thirdparty.services;

import com.yody.common.core.dto.Result;
import com.yody.common.thirdparty.request.PermissionRequestDto;
import com.yody.common.thirdparty.response.PermissionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountService {
    private final AccountServiceClient accountServiceClient;

    public AccountService(AccountServiceClient accountServiceClient) {
        this.accountServiceClient = accountServiceClient;
    }

    public Result<PermissionResponseDto> getPermissionInfo(PermissionRequestDto request) {
        ParameterizedTypeReference<Result<PermissionResponseDto>> parameterizedTypeReference
            = new ParameterizedTypeReference<Result<PermissionResponseDto>>() {
        };
        Result<PermissionResponseDto> result = accountServiceClient.get(request, parameterizedTypeReference);
        return result;
    }
}
