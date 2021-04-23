package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GoodsEnum  implements BaseEnum<String> {
    FOOTWEAR("foot_wear", "Giày dép"),
    CLOTHES("clothes", "Quần áo"),
    ACCESSORIES("accessories", "Phụ kiện thời trang");

    private final String value;
    private final String displayName;

    public static GoodsEnum parse(String value) {
        for (GoodsEnum type : GoodsEnum.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }
}
