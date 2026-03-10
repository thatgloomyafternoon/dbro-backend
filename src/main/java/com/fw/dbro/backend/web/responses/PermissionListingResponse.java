package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.PermissionDTO;
import java.util.List;

public class PermissionListingResponse extends ListingResponse {

  private List<PermissionDTO> permissionDTOs;

  public PermissionListingResponse() {}

  public PermissionListingResponse(Long totalCount, List<PermissionDTO> permissionDTOs) {
    super(totalCount);
    this.permissionDTOs = permissionDTOs;
  }

  public List<PermissionDTO> getPermissionDTOs() {
    return permissionDTOs;
  }

  public void setPermissionDTOs(List<PermissionDTO> permissionDTOs) {
    this.permissionDTOs = permissionDTOs;
  }

}
