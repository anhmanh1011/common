package com.yody.common.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata implements Serializable {
    private long total;
    private int page;
    private int limit;
}
