package com.yody.common.core.dto;

import lombok.Data;
@Data
public class PageableDto {
    private Metadata metadata;
    private Object items;
}
@Data
class Metadata{
    private int total;
    private int page;
    private int limit;
}
