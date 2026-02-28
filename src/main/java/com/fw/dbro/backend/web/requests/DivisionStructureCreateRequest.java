package com.fw.dbro.backend.web.requests;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

public class DivisionStructureCreateRequest {

  @NotNull(message = DIVISION_ID_NOT_NULL_MESSAGE)
  private UUID parentDivisionId;

  @NotNull(message = DIVISION_ID_NOT_NULL_MESSAGE)
  private UUID childDivisionId;

  public UUID getParentDivisionId() {
    return parentDivisionId;
  }

  public void setParentDivisionId(UUID parentDivisionId) {
    this.parentDivisionId = parentDivisionId;
  }

  public UUID getChildDivisionId() {
    return childDivisionId;
  }

  public void setChildDivisionId(UUID childDivisionId) {
    this.childDivisionId = childDivisionId;
  }

}
