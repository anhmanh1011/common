package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ScorecardEnum implements BaseEnum<Integer> {
    APLUS(1, "A+"),
    A(2, "A"),
    AMINUS(3, "A-"),
    BPLUS(4, "B+"),
    B(5, "B"),
    BMINUS(6, "B-"),
    CPLUS(7, "C+"),
    C(8, "C"),
    CMINUS(9, "C-");
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
