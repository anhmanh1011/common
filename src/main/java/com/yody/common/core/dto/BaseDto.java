package com.yody.common.core.dto;

import java.util.Date;
import lombok.Data;

@Data
public class BaseDto {
    protected Integer version;
    protected String createdBy;
    protected Date createdDate;
    protected String updatedBy;
    protected Date updatedDate;
}
