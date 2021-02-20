package com.yody.common.utility;

import java.util.Collections;
import java.util.Map;

public final class Maps {

  public static void clear(Map<?, ?> m) {
    if (m != null) {
      m.clear();
    }
  }

  public static final int size(Map<?, ?> m) {
    return m == null ? 0 : m.size();
  }

  public static final boolean isEmpty(Map<?, ?> m) {
    return m == null || m.isEmpty();
  }

  public static <K, V> Map<K, V> nullToEmpty(Map<K, V> m) {
    if (m != null) {
      return m;
    } else {
      return Collections.emptyMap();
    }
  }

}
