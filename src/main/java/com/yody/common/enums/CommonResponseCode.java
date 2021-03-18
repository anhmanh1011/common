package com.yody.common.enums;

import lombok.AllArgsConstructor;

 @AllArgsConstructor
public enum CommonResponseCode implements BaseEnum<Integer> {
     SUCCESS(20000000, "Thành công"),
     CREATED(20100000,"Tạo mới thành công"),
     BAD_REQUEST(40000000, "Request không hợp lệ"),
     UNAUTHORIZE(40100000,"Không có quyền truy cập"),
     FORBIDDEN(40300000,"Không đủ quyền truy cập"),
     NOT_FOUND(40400000,"Không tìm thấy data"),
     INTERNAL_ERROR(50000000, "Máy chủ đang bận"),
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
