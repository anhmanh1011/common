package com.yody.common.core;


import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

@Data
public class BaseEntity {
    @Id
    @Column(name = "Id", nullable = false, length = 50)
    protected String id;
    @Column(name = "updated_by", nullable = false)
    protected String updatedBy;
    @Column(name = "created_by", nullable = false)
    protected String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    protected Date updatedDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    protected Date createdDate;

    @PrePersist
    protected void onCreate() {
        this.id = UUID.randomUUID().toString();
        this.updatedDate = this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = new Date();
    }

}
