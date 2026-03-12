package com.fw.dbro.backend.web.requests;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

public class SysconfigCreateRequest {

  @NotNull(message = ID_NOT_NULL_MESSAGE)
  private UUID sysconfigTypeId;

  @Size(min = 1, max = SYSCONFIG_KEY_COLUMN_MAX_LENGTH, message = SYSCONFIG_KEY_NOT_EXCEEDS_MAX_MESSAGE + SYSCONFIG_KEY_COLUMN_MAX_LENGTH)
  @NotNull(message = SYSCONFIG_KEY_NOT_NULL_MESSAGE)
  @NotBlank(message = SYSCONFIG_KEY_NOT_BLANK_MESSAGE)
  private String key;

  @Size(min = 1, max = SYSCONFIG_VALUE_COLUMN_MAX_LENGTH, message = SYSCONFIG_VALUE_NOT_EXCEEDS_MAX_MESSAGE + SYSCONFIG_VALUE_COLUMN_MAX_LENGTH)
  @NotNull(message = SYSCONFIG_VALUE_NOT_NULL_MESSAGE)
  @NotBlank(message = SYSCONFIG_VALUE_NOT_BLANK_MESSAGE)
  private String value;

  public UUID getSysconfigTypeId() {
    return sysconfigTypeId;
  }

  public void setSysconfigTypeId(UUID sysconfigTypeId) {
    this.sysconfigTypeId = sysconfigTypeId;
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
