package com.fw.dbro.backend.web.responses;

import java.util.UUID;

public class SimpleIdResponse {

  private UUID id;

  public SimpleIdResponse() {}

  public SimpleIdResponse(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

}
