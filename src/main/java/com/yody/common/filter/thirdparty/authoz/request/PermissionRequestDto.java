package com.yody.common.filter.thirdparty.authoz.request;

import com.yody.common.core.dto.Request;

public class PermissionRequestDto extends Request {
    public PermissionRequestDto(){
        this.setPath("/auth/profile");
    }
}
