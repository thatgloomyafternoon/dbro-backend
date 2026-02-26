package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.ClockIn;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClockInRepository extends JpaRepository<ClockIn, UUID> {

  /**
   *
   */
  @Query(
    value = "select ci from ClockIn ci " +
            "where ci.deletedFlag = false"
  )
  List<ClockIn> findAllActive();

  /**
   *
   */
  @Query(
    value = "select ci from ClockIn ci " +
            "where ci.divisionUserAuth.id = ?1 and " +
            "ci.deletedFlag = false " +
            "order by ci.createdDate desc"
  )
  List<ClockIn> findAllActiveByDivisionUserAuthIdOrderCreatedDateDesc(UUID divisionUserAuthId);

  /**
   *
   */
  @Query(
    value = "select ci from ClockIn ci " +
            "where ci.divisionUserAuth.userAuth.id = ?1 " +
            "order by ci.createdDate desc"
  )
  List<ClockIn> findAllByUserAuthIdOrderCreatedDateDesc(UUID userAuthId);

  /**
   *
   */
  @Query(
    value = "select ci from ClockIn ci " +
            "where ci.divisionUserAuth.userAuth.id = ?1 and " +
            "ci.createdDate >= ?2 and ci.createdDate <= ?3"
  )
  List<ClockIn> findAllByUserAuthIdAndDateRange(UUID userAuthId, ZonedDateTime startDate, ZonedDateTime endDate);

}
