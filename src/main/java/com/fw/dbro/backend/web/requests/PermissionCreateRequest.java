package com.fw.dbro.backend.web.requests;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

public class PermissionCreateRequest {

  @NotNull(message = ROLE_NOT_NULL_MESSAGE)
  private UUID roleId;

  @NotNull(message = API_PATH_ID_LIST_NOT_NULL_MESSAGE)
  private UUID apiPathId;

  public UUID getRoleId() {
    return roleId;
  }

  public void setRoleId(UUID roleId) {
    this.roleId = roleId;
  }

  public UUID getApiPathId() {
    return apiPathId;
  }

  public void setApiPathId(UUID apiPathId) {
    this.apiPathId = apiPathId;
  }

}
