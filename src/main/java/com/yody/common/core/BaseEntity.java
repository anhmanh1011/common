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
    private boolean isDeleted;

    @Column(name = "version", columnDefinition = "smallint",nullable = false)
    protected int version;

    @Column(name = "created_by", nullable = false, length = 50)
    protected String createdBy;

    @Column(name = "created_name", nullable = false, length = 50)
    protected String createdName;

    @Column(name = "updated_by", nullable = false, length = 50)
    protected String updatedBy;

    @Column(name = "updated_name", nullable = false, length = 50)
    protected String updatedName;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    protected Date updatedDate;


    @PrePersist
    protected void onCreate() {
        this.version = 1;
        this.updatedDate = this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
        this.version +=1;
    }

}
