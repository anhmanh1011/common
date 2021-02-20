package com.yody.common.core.event;

import com.yody.common.core.domain.ID;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class DomainEvent<T extends ID> implements Serializable {

  public final T id;
  public final int version;
  public final long timestamp;
}
