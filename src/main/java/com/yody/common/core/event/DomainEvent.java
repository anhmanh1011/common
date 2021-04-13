package com.yody.common.core.event;

import com.yody.common.core.domain.ID;
import java.io.Serializable;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

@RequiredArgsConstructor
public abstract class DomainEvent implements Serializable {

  //public final T id;
  public final int version;
  public final long timestamp;
}
