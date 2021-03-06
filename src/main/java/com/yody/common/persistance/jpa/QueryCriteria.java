package com.yody.common.persistance.jpa;

import com.yody.common.enums.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QueryCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;

}
