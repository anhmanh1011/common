package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SearchOperation implements BaseEnum<String> {
    GREATER_THAN("gt", "gt"),
    LESS_THAN("lt", "lt"),
    GREATER_THAN_EQUAL("gte", "gte"),
    LESS_THAN_EQUAL("lte", "lte"),
    NOT_EQUAL("neq", "neq"),
    EQUAL("eq", "eq"),
    MATCH("like", "like"),
    MATCH_START("like_start", "like_start"),
    MATCH_END("like_end", "like_end"),
    IN("in", "in"),
    NOT_IN("not_in", "not_in");
    private String value;
    private String displayName;

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }
    public static SearchOperation parse (String value) {
        for(SearchOperation v: SearchOperation.values()) {
            if(v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
