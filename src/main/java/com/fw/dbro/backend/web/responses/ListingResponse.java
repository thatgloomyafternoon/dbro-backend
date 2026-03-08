package com.fw.dbro.backend.web.responses;

public abstract class ListingResponse {

  private Long totalCount;

  protected ListingResponse() {}

  protected ListingResponse(Long totalCount) {
    this.totalCount = totalCount;
  }

  public Long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(Long totalCount) {
    this.totalCount = totalCount;
  }

}
