package com.fw.dbro.backend.web.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

public class DivisionCreateRequest {

  @NotNull(message = ROOT_FLAG_NOT_NULL_MESSAGE)
  private Boolean rootFlag;

  @Size(min = 3, max = NAME_COLUMN_MAX_LENGTH, message = NAME_TOO_SHORT_OR_TOO_LONG_MESSAGE)
  @NotNull(message = NAME_NOT_NULL_MESSAGE)
  @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
  private String name;

  @NotNull(message = OUTLET_FLAG_NOT_NULL_MESSAGE)
  private Boolean outletFlag;

  public Boolean getRootFlag() {
    return rootFlag;
  }

  public void setRootFlag(Boolean rootFlag) {
    this.rootFlag = rootFlag;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getOutletFlag() {
    return outletFlag;
  }

  public void setOutletFlag(Boolean outletFlag) {
    this.outletFlag = outletFlag;
  }

}
