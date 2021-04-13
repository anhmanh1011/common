package com.yody.common.core.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class BaseDto {
    protected Long id;
    protected String code;
    protected Integer version;
    protected String createdBy;
    protected String createdName;
    protected long createdDate;
    protected String updatedBy;
    protected String updatedName;
    protected long updatedDate;
}
