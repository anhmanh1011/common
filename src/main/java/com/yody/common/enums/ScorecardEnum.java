package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScorecardEnum implements BaseEnum<Integer> {
    APLUS(1, "A+"),
    A(2, "A"),
    AMINUS(3, "A-"),
    BPLUS(1, "B+"),
    B(2, "B"),
    BMINUS(3, "B-"),
    CPLUS(1, "C+"),
    C(2, "C"),
    CMINUS(3, "C-");
    private Integer value;
    private String displayName;

    public static ScorecardEnum parse(Integer value) {
        for (ScorecardEnum type : ScorecardEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
