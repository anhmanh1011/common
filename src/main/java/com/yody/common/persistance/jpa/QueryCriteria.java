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
    private boolean isDate;
    public QueryCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

}
