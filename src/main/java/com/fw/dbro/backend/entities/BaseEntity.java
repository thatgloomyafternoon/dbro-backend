package com.fw.dbro.backend.entities;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

@MappedSuperclass
public abstract class BaseEntity {

  @Id
  @Type(type = "uuid-char")
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(name = "created_date")
  @CreationTimestamp
  private ZonedDateTime createdDate;

  @Column(name = "created_by", length = EMAIL_COLUMN_MAX_LENGTH)
  @Size(min = EMAIL_COLUMN_MIN_LENGTH, max = EMAIL_COLUMN_MAX_LENGTH)
  @NotNull(message = CREATED_BY_NOT_NULL_MESSAGE)
  @NotBlank(message = CREATED_BY_NOT_BLANK_MESSAGE)
  private String createdBy;

  @Column(name = "last_modified_date")
  @UpdateTimestamp
  private ZonedDateTime lastModifiedDate;

  @Column(name = "last_modified_by", length = EMAIL_COLUMN_MAX_LENGTH)
  @Size(min = EMAIL_COLUMN_MIN_LENGTH, max = EMAIL_COLUMN_MAX_LENGTH)
  @NotNull
  @NotBlank
  private String lastModifiedBy;

  @Column(name = "deleted_flag")
  @NotNull
  private Boolean deletedFlag = false;

  @Column(name = "deleted_date", columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private ZonedDateTime deletedDate;

  @Column(name = "deleted_by")
  private String deletedBy;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ZonedDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(ZonedDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public ZonedDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public Boolean getDeletedFlag() {
    return deletedFlag;
  }

  public void setDeletedFlag(Boolean deletedFlag) {
    this.deletedFlag = deletedFlag;
  }

  public ZonedDateTime getDeletedDate() {
    return deletedDate;
  }

  public void setDeletedDate(ZonedDateTime deletedDate) {
    this.deletedDate = deletedDate;
  }

  public String getDeletedBy() {
    return deletedBy;
  }

  public void setDeletedBy(String deletedBy) {
    this.deletedBy = deletedBy;
  }

}
