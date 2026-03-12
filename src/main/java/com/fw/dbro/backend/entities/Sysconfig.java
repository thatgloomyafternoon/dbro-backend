package com.fw.dbro.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

@Table(name = "sysconfig")
@Entity
public class Sysconfig extends BaseEntity {

  @JoinColumn(name = "sysconfig_type_id")
  @ManyToOne
  @NotNull
  private SysconfigType sysconfigType;

  @Column(name = "key", length = SYSCONFIG_KEY_COLUMN_MAX_LENGTH)
  @Size(min = 1, max = SYSCONFIG_KEY_COLUMN_MAX_LENGTH, message = SYSCONFIG_KEY_NOT_EXCEEDS_MAX_MESSAGE + SYSCONFIG_KEY_COLUMN_MAX_LENGTH)
  @NotNull(message = SYSCONFIG_KEY_NOT_NULL_MESSAGE)
  @NotBlank(message = SYSCONFIG_KEY_NOT_BLANK_MESSAGE)
  private String key;

  @Column(name = "value", length = SYSCONFIG_VALUE_COLUMN_MAX_LENGTH)
  @Size(min = 1, max = SYSCONFIG_VALUE_COLUMN_MAX_LENGTH, message = SYSCONFIG_VALUE_NOT_EXCEEDS_MAX_MESSAGE + SYSCONFIG_VALUE_COLUMN_MAX_LENGTH)
  @NotNull(message = SYSCONFIG_VALUE_NOT_NULL_MESSAGE)
  @NotBlank(message = SYSCONFIG_VALUE_NOT_BLANK_MESSAGE)
  private String value;

  public Sysconfig() {}

  public Sysconfig(SysconfigType sysconfigType, String key, String value) {
    this.sysconfigType = sysconfigType;
    this.key = key;
    this.value = value;
  }

  public SysconfigType getSysconfigType() {
    return sysconfigType;
  }

  public void setSysconfigType(SysconfigType sysconfigType) {
    this.sysconfigType = sysconfigType;
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
