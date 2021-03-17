package com.yody.common.core.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BaseDto {
    protected Integer version;
    protected String createdBy;
    protected String createdName;
    protected long createdDate;
    protected String updatedBy;
    protected String updatedName;
    protected long updatedDate;
}
