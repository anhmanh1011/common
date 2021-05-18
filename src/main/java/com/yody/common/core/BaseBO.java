package com.yody.common.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yody.common.core.domain.AggregateRoot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter
public class BaseBO<T extends AggregateRoot<T>> extends BaseEntity{
    @JsonIgnore
    protected  T root;
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected boolean create;
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected boolean modify;
}
