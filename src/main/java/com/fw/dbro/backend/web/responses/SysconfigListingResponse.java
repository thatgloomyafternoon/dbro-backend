package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.SysconfigDTO;
import java.util.List;

public class SysconfigListingResponse extends ListingResponse {

  private List<SysconfigDTO> sysconfigDTOs;

  public SysconfigListingResponse() {}

  public SysconfigListingResponse(Long totalCount, List<SysconfigDTO> sysconfigDTOs) {
    super(totalCount);
    this.sysconfigDTOs = sysconfigDTOs;
  }

  public List<SysconfigDTO> getSysconfigDTOs() {
    return sysconfigDTOs;
  }

  public void setSysconfigDTOs(List<SysconfigDTO> sysconfigDTOs) {
    this.sysconfigDTOs = sysconfigDTOs;
  }

}
