package com.yody.common.enums;

import lombok.AllArgsConstructor;

 @AllArgsConstructor
public enum CommonResponseCode implements BaseEnum<Integer> {
     SUCCESS(0, "sucess"),
     CREATED(201,"created"),
     BAD_REQUEST(400, "invalid request"),
     UNAUTHORIZE(401,"unauthorized"),
     FORBIDDEN(403,"Forbidden"),
     NOT_FOUND(404,"Not found"),
     INTERNAL_ERROR(500, "internal server error"),
     BAD_GATEWAY(502,"Bad gateway");

     private final int value;
     private final String displayName;
     @Override
     public Integer getValue() {
         return null;
     }
     @Override
     public String getDisplayName() {
         return null;
     }
 }
