package com.yody.common.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yody.common.core.domain.AggregateRoot;
import com.yody.common.utility.Dates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class BaseBO<T extends AggregateRoot<T>> extends BaseEntity {
  private final List<Event> events = new ArrayList<>();

  @JsonIgnore
  protected T root;
  @JsonIgnore
  @Setter(AccessLevel.PROTECTED)
  protected boolean create;
  @JsonIgnore
  @Setter(AccessLevel.PROTECTED)
  protected boolean modify;

  protected void _setCreate() {
    this.create = true;
    this.createdDate = Dates.getUTC();
    this.createdBy = root.isCreate() ? root.getCreatedBy() : root.getUpdatedBy();
    this.createdName = root.isCreate() ? root.getCreatedName() : root.getUpdatedName();
    _setModify();
  }

  protected void _setModify() {
    this.modify = true;
    this.updatedBy = root.getUpdatedBy();
    this.updatedName = root.getUpdatedName();
    this.updatedDate = Dates.getUTC();
  }

  protected void addEvents(Event event) {
    events.add(event);
  }

  protected void clearEvents() {
    events.clear();
  }

  protected void removeEvents(Event event) {
    events.remove(event);
  }

  protected int nextVersion() {
    return version++;
  }

}
