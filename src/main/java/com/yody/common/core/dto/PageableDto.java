package com.yody.common.core.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableDto<T> implements Serializable {
    private Metadata metadata;
    private List<T> items;
}

