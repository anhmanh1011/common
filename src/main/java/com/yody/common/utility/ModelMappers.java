package com.yody.common.utility;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;

public class ModelMappers {

  private static ModelMapper mapper = null;

  private static ModelMapper getMapper() {
    if (mapper == null) {
      mapper = new ModelMapper();
      mapper.getConfiguration().setAmbiguityIgnored(true);
    }
    return mapper;
  }


  public static <S, T> T map(S source, Class<T> target) {
    return getMapper().map(source, target);
  }

  public static <S, T> List<T> mapList(List<S> source, Class<T> target) {
    if (source == null) {
      return null;
    }
    return source.stream().map(element -> getMapper().map(element, target)).collect(Collectors.toList());
  }

  public static <S, T> T mapWithoutSetter(S source, Class<T> target) {
    if (source == null) {
      return null;
    }
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
    return mapper.map(source, target);
  }

  public static <S, T> void mapToWithoutSetter(S source, T target) {
    if (source == null || target == null) {
      return;
    }
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
    mapper.map(source, target);
  }


  public static <S, T> List<T> mapListWithoutSetter(List<S> source, Class<T> target) {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration().setFieldAccessLevel(AccessLevel.PRIVATE).setFieldMatchingEnabled(true);
    return source.stream().map(element -> mapper.map(element, target)).collect(Collectors.toList());
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
