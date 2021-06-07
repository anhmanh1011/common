package com.yody.common.core.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Date;
import lombok.Data;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class BaseDto {
    protected Long id;
    protected String code;
    protected Integer version;
    protected String createdBy;
    protected String createdName;
    protected Date createdDate;
    protected String updatedBy;
    protected String updatedName;
    protected Date updatedDate;
}
