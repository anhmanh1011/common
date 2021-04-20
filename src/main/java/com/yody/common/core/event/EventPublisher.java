package com.yody.common.core.event;

public interface EventPublisher {

  <T extends Enum> void publish(String topic, T event);
}
