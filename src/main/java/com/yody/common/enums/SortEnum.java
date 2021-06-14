package com.yody.common.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SortEnum implements BaseEnum<String>{
    ASC("asc", "ASC"),
    DESC("desc","DESC");

    private final String value;
    private final String displayName;
    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    public static SortEnum parse(String value) {
        for (SortEnum sort : SortEnum.values()) {
            if (sort.getValue().equals(value)) {
                return sort;
            }
        }
        return null;
    }
}
