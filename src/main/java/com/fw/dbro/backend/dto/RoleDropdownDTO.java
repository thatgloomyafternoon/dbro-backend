package com.fw.dbro.backend.dto;

import java.util.UUID;

public class RoleDropdownDTO {

  private UUID roleId;
  private String name;

  public UUID getRoleId() {
    return roleId;
  }

  public void setRoleId(UUID roleId) {
    this.roleId = roleId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
