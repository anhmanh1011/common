package com.yody.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class PageableDto {
    private Metadata metadata;
    private Object items;

    @Data
    @AllArgsConstructor
    public class Metadata{
        private int total;
        private int page;
        private int limit;
    }
}

