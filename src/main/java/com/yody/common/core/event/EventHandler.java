package com.yody.common.core.event;

public interface EventHandler<T extends Enum> {

  void handle(T event);
}
