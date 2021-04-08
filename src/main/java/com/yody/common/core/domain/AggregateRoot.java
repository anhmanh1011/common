package com.yody.common.core.domain;

import com.yody.common.core.BaseEntity;
import com.yody.common.core.event.DomainEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

@Getter
public abstract class AggregateRoot<T> extends BaseEntity{

 // private AtomicInteger version = new AtomicInteger(0);
  private long timestamp = 0;
  private final String APPLY_METHOD = "apply";
  private List<DomainEvent> pendingEvents = new ArrayList<>();
  protected int nextVersion() {
    return version++;
  }

  protected void addEvents(DomainEvent event) {
    pendingEvents.add(event);
  }

  protected void removeEvents(DomainEvent event) {
    pendingEvents.remove(event);
  }

  protected void clearEvents() {
    pendingEvents.clear();
  }

  protected void applyChange(DomainEvent event) {
    applyChange(event, true);
  }

  private void applyChange(DomainEvent event, boolean isNew) {
    updateMetadata(event);
    invokeHandlerMethod(event);
    if (isNew) {
      pendingEvents.add(event);
    }
  }

  private void updateMetadata(DomainEvent event) {
    this.version = event.version;
    this.timestamp = event.timestamp;
  }

  // call method apply event in root
  private void invokeHandlerMethod(DomainEvent event) {
    Method handlerMethod = getHandlerMethod(event);
    if (handlerMethod != null) {
      handlerMethod.setAccessible(true);
      try {
        handlerMethod.invoke(this, event);
      } catch (Exception e) {
        throw new RuntimeException("Unable to call event handler method for " + event.getClass().getName(), e);
      }
    }
  }

  private Method getHandlerMethod(DomainEvent event) {
    try {
      return getClass().getDeclaredMethod(APPLY_METHOD, event.getClass());
    } catch (NoSuchMethodException e) {
      return null;
    }
  }
}
