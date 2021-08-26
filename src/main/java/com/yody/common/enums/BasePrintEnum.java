package com.yody.common.enums;

public interface BasePrintEnum<T> {

    T getValue();

    String getDisplayName();

    String getPreviewValue();
}
