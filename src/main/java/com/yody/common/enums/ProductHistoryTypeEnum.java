package com.yody.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProductHistoryTypeEnum implements BaseEnum<String>{
    ADD_PRODUCT("ADD_PRODUCT", "Thêm mới sản phẩm"),
    ADD_VARIANT("ADD_VARIANT", "Thêm mới phiên bản"),
    UPDATE_VARIANT("UPDATE_VARIANT", "Cập nhật thông tin phiên bản"),
    DELETE_VARIANT("DELETE_VARIANT", "Xóa phiên bản"),
    UPDATE_PRODUCT("UPDATE_PRODUCT", "Cập nhật thông tin sản phẩm"),
    UPDATE_PRICE("UPDATE_PRICE", "Cập nhật giá phiên bản"),
    DELETE_PRODUCT("DELETE_PRODUCT", "Xóa sản phẩm");

    private String value;
    private String displayName;

    public static ProductHistoryTypeEnum parse(String value) {
        for (ProductHistoryTypeEnum v : ProductHistoryTypeEnum.values()) {
            if (v.getValue().equals(value)) {
                return v;
            }
        }
        return null;
    }
}
