package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GoodsEnum  implements BaseEnum<Integer> {
    FOOTWEAR(1, "Giày dép"),
    CLOTHES(2, "Quàn áo"),
    ACCESSORIES(3, "Phụ kiện thời trang");

    private final Integer value;
    private final String displayName;

    public static GoodsEnum parse(Integer value) {
        for (GoodsEnum type : GoodsEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
