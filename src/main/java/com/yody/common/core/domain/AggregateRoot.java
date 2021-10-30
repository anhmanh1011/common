package com.yody.common.core.domain;

import com.yody.common.core.BaseBO;
import lombok.Getter;

@Getter
public abstract class AggregateRoot<T extends AggregateRoot<T>> extends BaseBO<T> {
  private long timestamp = 0;
  private final String APPLY_METHOD = "apply";
}
