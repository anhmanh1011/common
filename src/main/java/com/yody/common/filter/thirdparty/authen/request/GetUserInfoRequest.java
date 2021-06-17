package com.yody.common.filter.thirdparty.authen.request;

import com.yody.common.core.dto.Request;
import lombok.Data;

@Data
public class GetUserInfoRequest extends Request {
    private String token;

    public GetUserInfoRequest(){
        this.setPath("/auth/realms/Yody/protocol/openid-connect/userinfo");
    }
}
