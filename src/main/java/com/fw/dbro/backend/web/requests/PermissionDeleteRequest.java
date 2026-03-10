package com.fw.dbro.backend.web.requests;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

public class PermissionDeleteRequest {

  @NotNull(message = PERMISSION_ID_NOT_NULL_MESSAGE)
  private UUID permissionId;

  public UUID getPermissionId() {
    return permissionId;
  }

  public void setPermissionId(UUID permissionId) {
    this.permissionId = permissionId;
  }

}
