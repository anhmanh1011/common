package com.yody.common.utility;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

public class ModelMappers {

  private static ModelMapper mapper = null;

  private static ModelMapper getMapper() {
    if (mapper == null) {
      mapper = new ModelMapper();
      mapper.getConfiguration()
          // .setFieldMatchingEnabled(true)
          //.setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
          .setAmbiguityIgnored(true);
    }
    return mapper;
  }

  public static <S, T> T map(S source, Class<T> target) {
    return getMapper().map(source, target);
  }

  public static <S, T> List<T> mapList(List<S> source, Class<T> target) {
    return source
        .stream()
        .map(element -> getMapper().map(element, target))
        .collect(Collectors.toList());
  }

  public static <S, T> void mapTo(S source, T target) {
    try {
      if (source == null || target == null) {
        throw new Exception("Objects null");
      }
      getMapper().map(source, target);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
