package com.fw.dbro.backend.web.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

public class SysconfigTypeCreateRequest {

  @Size(min = 1, max = SYSCONFIG_TYPE_NAME_COLUMN_MAX_LENGTH, message = SYSCONFIG_TYPE_NAME_TOO_SHORT_OR_TOO_LONG_MESSAGE)
  @NotNull(message = SYSCONFIG_TYPE_NAME_NOT_NULL_MESSAGE)
  @NotBlank(message = SYSCONFIG_TYPE_NAME_NOT_BLANK_MESSAGE)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
