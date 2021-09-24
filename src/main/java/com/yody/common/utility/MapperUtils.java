package com.yody.common.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

public class MapperUtils {

  private static ObjectMapper objectMapper = null;

  private MapperUtils() {
  }

  public static ObjectMapper getObjectMapper() {
    if (objectMapper == null) {
      objectMapper = new ObjectMapper().configure(
          DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    return objectMapper;
  }

  public static <T> T jsonToObject(String source, Class<T> target) {
    if (StringUtils.isBlank(source)) {
      return null;
    }
    try {
      return getObjectMapper().readValue(source, target);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static String objectToJson(Object o) {
    try {
      return getObjectMapper().writeValueAsString(o);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static <T> T convert(Object fromValue, Class<T> toValueType) {
    try {
      return getObjectMapper().convertValue(fromValue, toValueType);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return null;
    }
  }
}
