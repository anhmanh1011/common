package com.yody.common.thirdparty.request;

import com.yody.common.core.dto.Request;

public class PermissionRequestDto extends Request {
    public PermissionRequestDto(){
        this.setPath("/permission/user-info");
    }
}
