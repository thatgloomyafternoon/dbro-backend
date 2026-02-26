package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.DivisionListingDTO;
import java.util.List;

public class DivisionListingResponse extends ListingResponse {

  private List<DivisionListingDTO> divisionListingDTOs;

  public DivisionListingResponse() {}

  public DivisionListingResponse(Long totalCount, List<DivisionListingDTO> divisionListingDTOs) {
    super(totalCount);
    this.divisionListingDTOs = divisionListingDTOs;
  }

  public List<DivisionListingDTO> getDivisionListingDTOs() {
    return divisionListingDTOs;
  }

  public void setDivisionListingDTOs(List<DivisionListingDTO> divisionListingDTOs) {
    this.divisionListingDTOs = divisionListingDTOs;
  }

}
