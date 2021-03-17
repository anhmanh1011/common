package com.yody.common.enums;

import lombok.AllArgsConstructor;

 @AllArgsConstructor
public enum CommonResponseCode implements BaseEnum<Integer> {
     SUCCESS(20000000, "Thành công"),
     CREATED(20100000,"Tạo mới thành công"),
     BAD_REQUEST(40000000, "invalid request"),
     UNAUTHORIZE(40100000,"unauthorized"),
     FORBIDDEN(40300000,"Forbidden"),
     NOT_FOUND(40400000,"Not found"),
     INTERNAL_ERROR(50000000, "internal server error"),
     BAD_GATEWAY(50200000,"Bad gateway");

     private final int value;
     private final String displayName;
     @Override
     public Integer getValue() {
         return this.value;
     }
     @Override
     public String getDisplayName() {
         return this.displayName;
     }
 }
