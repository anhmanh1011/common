package com.yody.common.core.event;

public interface EventHandler<T extends DomainEvent> {

  void handle(T event);
}
