package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScorecardEnum implements BaseEnum<String> {
    APLUS("A+", "A+"),
    A("A", "A"),
    AMINUS("A-", "A-"),
    BPLUS("B+", "B+"),
    B("B", "B"),
    BMINUS("B-", "B-"),
    CPLUS("C+", "C+"),
    C("C", "C"),
    CMINUS("C-", "C-");
    private String value;
    private String displayName;

    public static ScorecardEnum parse(String value) {
        for (ScorecardEnum type : ScorecardEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
