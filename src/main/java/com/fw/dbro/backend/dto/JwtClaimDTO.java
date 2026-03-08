package com.fw.dbro.backend.dto;

import java.util.UUID;

public class JwtClaimDTO {

  private UUID userAuthId;
  private String email;
  private UUID roleId;

  public JwtClaimDTO(UUID userAuthId, String email, UUID roleId) {
    this.userAuthId = userAuthId;
    this.email = email;
    this.roleId = roleId;
  }

  public UUID getUserAuthId() {
    return userAuthId;
  }

  public void setUserAuthId(UUID userAuthId) {
    this.userAuthId = userAuthId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public UUID getRoleId() {
    return roleId;
  }

  public void setRoleId(UUID roleId) {
    this.roleId = roleId;
  }

}
