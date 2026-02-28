package com.fw.dbro.backend.web.requests;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

public class DivisionRemoveUserRequest {

  @NotNull(message = DIVISION_ID_NOT_NULL_MESSAGE)
  private UUID divisionId;

  @NotNull(message = USER_AUTH_ID_NOT_NULL_MESSAGE)
  private UUID userAuthId;

  public UUID getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(UUID divisionId) {
    this.divisionId = divisionId;
  }

  public UUID getUserAuthId() {
    return userAuthId;
  }

  public void setUserAuthId(UUID userAuthId) {
    this.userAuthId = userAuthId;
  }

}
