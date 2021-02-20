package com.yody.common.core.domain;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Data
public class ID implements Serializable {

  private static final long serialVersionUID = 1L;

  private final String id;

  public static ID fromObject(Object id) {
    if (id instanceof String) {
      return new ID((String) id);
    } else if (id instanceof UUID) {
      return new ID(id.toString());
    } else {
      new IllegalArgumentException("The id should be of either String or UUID type");
    }

    return null;
  }

}
