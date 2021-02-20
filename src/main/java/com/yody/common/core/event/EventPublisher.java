package com.yody.common.core.event;

public interface EventPublisher {

  <T extends DomainEvent> void publish(String topic, T event);
}
