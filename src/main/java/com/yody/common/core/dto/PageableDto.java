package com.yody.common.core.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
public class PageableDto implements Serializable {
    private Metadata metadata;
    private Object items;

    @Data
    @AllArgsConstructor
    public class Metadata{
        private long total;
        private int page;
        private int limit;
    }
}

