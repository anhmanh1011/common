package com.yody.common.core;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "is_deleted", columnDefinition = "tinyint(1) default 0")
    private boolean is_deleted;

    @Column(name = "version", columnDefinition = "smallint",nullable = false)
    protected int version;

    @Column(name = "created_by", nullable = false, length = 50)
    protected String created_by;

    @Column(name = "created_name", nullable = false)
    protected String created_name;

    @Column(name = "updated_by", nullable = false, length = 50)
    protected String updated_by;

    @Column(name = "updated_name", nullable = false)
    protected String updated_name;

    @Column(name = "created_date")
    protected Long created_date;

    @Column(name = "updated_date")
    protected Long updated_date;


    @PrePersist
    protected void onCreate() {
        this.version = 1;
        this.created_date = this.updated_date = System.currentTimeMillis();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_date = System.currentTimeMillis();
        this.version +=1;
    }

}
