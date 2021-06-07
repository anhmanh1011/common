package com.yody.common.core;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class BaseSearchRequest implements Serializable {
    private int page;
    private int limit;
    private Date fromDate;
    private Date toDate;
}
