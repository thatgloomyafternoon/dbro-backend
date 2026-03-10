package com.fw.dbro.backend.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

public class PermissionDTO {

  private UUID id;
  private ZonedDateTime createdDate;
  private String createdBy;
  private ZonedDateTime lastModifiedDate;
  private String lastModifiedBy;
  private String role;
  private UUID apiPathId;
  private String apiPathKey;
  private String apiPathValue;

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

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public UUID getApiPathId() {
    return apiPathId;
  }

  public void setApiPathId(UUID apiPathId) {
    this.apiPathId = apiPathId;
  }

  public String getApiPathKey() {
    return apiPathKey;
  }

  public void setApiPathKey(String apiPathKey) {
    this.apiPathKey = apiPathKey;
  }

  public String getApiPathValue() {
    return apiPathValue;
  }

  public void setApiPathValue(String apiPathValue) {
    this.apiPathValue = apiPathValue;
  }

}
