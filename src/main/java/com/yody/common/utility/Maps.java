package com.yody.common.utility;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.yody.common.core.dto.BaseDto;

import java.util.*;

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

  public static final Map<Object, Object> toMap(final Object... kvs) {
    if (kvs == null || kvs.length % 2 != 0) {
      throw new IllegalArgumentException("invalid parameter kvs: " + kvs);
    } else {
      final Map<Object, Object> r = new HashMap<>();
      for (int i = 0; i < kvs.length; i += 2) {
        r.put(kvs[(i)], kvs[i + 1]);
      }
      return r;
    }
  }

  public static final <K, V> Map<K, V> toMap(K key, V value) {
    Map<K, V> r = new HashMap<>();
    r.put(key, value);
    return r;
  }

  public static final <K, V> Map<K, V> toMap(K k1, V v1, K k2, V v2) {
    Map<K, V> r = new HashMap<>();
    r.put(k1, v1);
    r.put(k2, v2);
    return r;
  }

  public static final Map<String, Object> toMap(List<String> fields, Object o) {
    Gson gson = new Gson();
    JsonElement json = gson.toJsonTree(o);
    Map<String, Object> map = new HashMap<>();
    fields.forEach(
        field -> {
          map.put(field, json.getAsJsonObject().get(Strings.snakeToCamel(field)));
        });
    return map;
  }

}
