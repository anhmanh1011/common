package com.yody.common.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter
public class BaseBO extends BaseEntity{
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected boolean create;
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected boolean modify;
}
