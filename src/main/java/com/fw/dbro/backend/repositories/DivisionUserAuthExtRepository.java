package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.DivisionUserAuth;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class DivisionUserAuthExtRepository {

  private DivisionUserAuthRepository divisionUserAuthRepository;

  public DivisionUserAuthExtRepository(DivisionUserAuthRepository divisionUserAuthRepository) {
    this.divisionUserAuthRepository = divisionUserAuthRepository;
  }

  /**
   *
   */
  public void softDelete(DivisionUserAuth divisionUserAuth, String deletedBy) {
    divisionUserAuth.setDeletedDate(ZonedDateTime.now());
    divisionUserAuth.setDeletedBy(deletedBy);
    divisionUserAuth.setDeletedFlag(true);
    divisionUserAuthRepository.save(divisionUserAuth);
  }

}
