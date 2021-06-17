package com.yody.common.filter.thirdparty.authen.services;

import com.yody.common.filter.thirdparty.authen.request.GetUserInfoRequest;
import com.yody.common.filter.thirdparty.authen.response.GetUserInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenService {
    private final AuthenServiceClient authenServiceClient;

    public AuthenService(AuthenServiceClient authenServiceClient) {
        this.authenServiceClient = authenServiceClient;
    }

    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) {
        ParameterizedTypeReference<GetUserInfoResponse> parameterizedTypeReference
            = new ParameterizedTypeReference<GetUserInfoResponse>() {
        };
        GetUserInfoResponse result = authenServiceClient.get(request, parameterizedTypeReference);
        return result;
    }
}
