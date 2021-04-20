package com.yody.common.core;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
  protected Long createdDate;

  @Column(name = "updated_date")
  protected Long updatedDate;

  @PrePersist
  protected void onCreate() {
    this.version = 1;
    this.createdDate = this.updatedDate = System.currentTimeMillis();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedDate = System.currentTimeMillis();
    this.version++;
  }
}
