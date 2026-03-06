package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.DivisionUserPayrollListingDTO;
import java.util.List;

public class DivisionUserPayrollListingResponse {

  private String divisionName;
  private List<DivisionUserPayrollListingDTO> divisionUserPayrollListingDTOs;

  public DivisionUserPayrollListingResponse() {}

  public DivisionUserPayrollListingResponse(
    String divisionName,
    List<DivisionUserPayrollListingDTO> divisionUserPayrollListingDTOs
  ) {
    this.divisionName = divisionName;
    this.divisionUserPayrollListingDTOs = divisionUserPayrollListingDTOs;
  }

  public String getDivisionName() {
    return divisionName;
  }

  public void setDivisionName(String divisionName) {
    this.divisionName = divisionName;
  }

  public List<DivisionUserPayrollListingDTO> getDivisionUserPayrollListingDTOs() {
    return divisionUserPayrollListingDTOs;
  }

  public void setDivisionUserPayrollListingDTOs(List<DivisionUserPayrollListingDTO> divisionUserPayrollListingDTOs) {
    this.divisionUserPayrollListingDTOs = divisionUserPayrollListingDTOs;
  }

}
