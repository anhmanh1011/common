package com.yody.common.core;

import com.yody.common.utility.Dates;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;

@MappedSuperclass
@Data
public class CustomBaseEntity {

  @Id
  @Column(name = "id")
  protected Long id;
  @Column(name = "code", nullable = false, length = 36)
  protected String code;
  @Column(name = "is_deleted", columnDefinition = "tinyint(1) default 0")
  protected boolean isDeleted;
  @Column(name = "version", columnDefinition = "smallint", nullable = false)
  protected int version;
  @Column(name = "created_by", nullable = false, length = 36)
  protected String createdBy;
  @Column(name = "created_name", nullable = false)
  protected String createdName;
  @Column(name = "updated_by", nullable = false, length = 36)
  protected String updatedBy;
  @Column(name = "updated_name", nullable = false)
  protected String updatedName;
  @Column(name = "created_date")
  protected Date createdDate;
  @Column(name = "updated_date")
  protected Date updatedDate;

  public CustomBaseEntity() {
  }

  @PrePersist
  protected void onCreate() {
    this.version = 1;
    this.createdDate = this.updatedDate = Dates.getUTC();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedDate = Dates.getUTC();
  }
}