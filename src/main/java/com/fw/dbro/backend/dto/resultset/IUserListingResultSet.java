package com.fw.dbro.backend.dto.resultset;

import java.time.LocalDate;
import java.util.UUID;

public interface IUserListingResultSet {

  UUID getId();
  String getName();
  LocalDate getJoinDate();
  String getCreatedBy();
  String getRole();
  String getDivision();

}
