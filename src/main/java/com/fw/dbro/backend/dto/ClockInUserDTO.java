package com.fw.dbro.backend.dto;

import java.util.UUID;

public class ClockInUserDTO {

  private UUID userAuthId;
  private String name;

  public UUID getUserAuthId() {
    return userAuthId;
  }

  public void setUserAuthId(UUID userAuthId) {
    this.userAuthId = userAuthId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
