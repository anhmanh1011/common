package com.yody.common.core;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseSearchRequest implements Serializable {
    private int page;
    private int limit;
}
