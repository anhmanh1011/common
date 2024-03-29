package com.yody.common.core.dto;

import static com.yody.common.filter.constant.DateTimeConstant.YYYYMMDD_T_HHMMSSZ;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YYYYMMDD_T_HHMMSSZ)
    protected Date createdDate;
    protected String updatedBy;
    protected String updatedName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = YYYYMMDD_T_HHMMSSZ)
    protected Date updatedDate;
}
