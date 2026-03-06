package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.DivisionUserListingDTO;
import java.util.List;

public class DivisionUserListingResponse extends ListingResponse {

  private List<DivisionUserListingDTO> divisionUserListingDTOs;

  public DivisionUserListingResponse() {}

  public DivisionUserListingResponse(Long totalCount, List<DivisionUserListingDTO> divisionUserListingDTOs) {
    super(totalCount);
    this.divisionUserListingDTOs = divisionUserListingDTOs;
  }

  public List<DivisionUserListingDTO> getDivisionUserListingDTOs() {
    return divisionUserListingDTOs;
  }

  public void setDivisionUserListingDTOs(List<DivisionUserListingDTO> divisionUserListingDTOs) {
    this.divisionUserListingDTOs = divisionUserListingDTOs;
  }

}
