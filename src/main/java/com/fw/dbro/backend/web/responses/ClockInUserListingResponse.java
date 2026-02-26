package com.fw.dbro.backend.web.responses;

import com.fw.dbro.backend.dto.ClockInUserDTO;
import java.util.List;
import java.util.UUID;

public class ClockInUserListingResponse extends ListingResponse {

  private UUID divisionId;
  private List<ClockInUserDTO> clockInUserDTOs;

  public ClockInUserListingResponse() {}

  public ClockInUserListingResponse(Long totalCount, UUID divisionId, List<ClockInUserDTO> clockInUserDTOs) {
    super(totalCount);
    this.divisionId = divisionId;
    this.clockInUserDTOs = clockInUserDTOs;
  }

  public UUID getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(UUID divisionId) {
    this.divisionId = divisionId;
  }

  public List<ClockInUserDTO> getClockInUserDTOs() {
    return clockInUserDTOs;
  }

  public void setClockInUserDTOs(List<ClockInUserDTO> clockInUserDTOs) {
    this.clockInUserDTOs = clockInUserDTOs;
  }

}
