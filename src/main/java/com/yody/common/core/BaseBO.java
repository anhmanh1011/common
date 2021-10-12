package com.yody.common.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yody.common.core.domain.AggregateRoot;
import com.yody.common.utility.Dates;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class BaseBO<T extends AggregateRoot<T>> extends BaseEntity {

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
}
