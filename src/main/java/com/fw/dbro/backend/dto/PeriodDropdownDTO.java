package com.fw.dbro.backend.dto;

import java.util.UUID;

public class PeriodDropdownDTO {

  private UUID periodId;
  private String key;
  private String value;

  public UUID getPeriodId() {
    return periodId;
  }

  public void setPeriodId(UUID periodId) {
    this.periodId = periodId;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}
