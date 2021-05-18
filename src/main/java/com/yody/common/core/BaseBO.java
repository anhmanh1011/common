package com.yody.common.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yody.common.core.domain.AggregateRoot;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
@Getter
public class BaseBO<T extends AggregateRoot<T>> extends BaseEntity{
    @JsonIgnore
    protected  T root;
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected boolean create;
    @JsonIgnore
    @Setter(AccessLevel.PROTECTED)
    protected boolean modify;

    protected  void _setCreate(){
        this.createdDate = System.currentTimeMillis();
        this.createdBy = root.isCreate() ? root.getCreatedBy() : root.getUpdatedBy();
        this.createdName = root.isCreate() ? root.getCreatedName() : root.getUpdatedName();
        _setModify();
    }
    protected void _setModify(){
        this.updatedBy = root.getUpdatedBy();
        this.updatedName = root.getUpdatedName();
        this.updatedDate = System.currentTimeMillis();
    }
}
