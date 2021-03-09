package com.yody.common.core.dto;

import lombok.Data;

@Data
public class BaseDto {
    protected Integer version;
    protected String created_by;
    protected String created_name;
    protected long created_date;
    protected String updated_by;
    protected String updated_name;
    protected long updated_date;
}
