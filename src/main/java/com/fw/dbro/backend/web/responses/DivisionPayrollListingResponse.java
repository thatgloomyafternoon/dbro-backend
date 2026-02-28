package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.DivisionPayrollListingDTO;
import java.util.List;

public class DivisionPayrollListingResponse extends ListingResponse {

  private List<DivisionPayrollListingDTO> divisionPayrollListingDTOs;

  public DivisionPayrollListingResponse() {}

  public DivisionPayrollListingResponse(Long totalCount, List<DivisionPayrollListingDTO> divisionPayrollListingDTOs) {
    super(totalCount);
    this.divisionPayrollListingDTOs = divisionPayrollListingDTOs;
  }

  public List<DivisionPayrollListingDTO> getDivisionPayrollListingDTOs() {
    return divisionPayrollListingDTOs;
  }

  public void setDivisionPayrollListingDTOs(List<DivisionPayrollListingDTO> divisionPayrollListingDTOs) {
    this.divisionPayrollListingDTOs = divisionPayrollListingDTOs;
  }

}
