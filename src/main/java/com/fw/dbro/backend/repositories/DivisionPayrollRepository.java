package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.DivisionPayroll;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DivisionPayrollRepository extends JpaRepository<DivisionPayroll, UUID> {

  /**
   *
   */
  @Query(
    value = "select dp from DivisionPayroll dp " +
            "where dp.deletedFlag = false and " +
            "dp.division.deletedFlag = false"
  )
  List<DivisionPayroll> findAllActive();

  /**
   *
   */
  @Query(
    value = "select dp from DivisionPayroll dp " +
            "where dp.period.id = ?1 and " +
            "dp.deletedFlag = false and " +
            "dp.division.deletedFlag = false")
  List<DivisionPayroll> findAllActiveByPeriodId(UUID periodId);

  /**
   *
   */
  @Query(
    value = "select dp from DivisionPayroll dp " +
            "where dp.id = ?1 and " +
            "dp.deletedFlag = false and " +
            "dp.division.deletedFlag = false"
  )
  Optional<DivisionPayroll> findOneActiveById(UUID id);

  /**
   *
   */
  @Query(
    value = "select dp from DivisionPayroll dp " +
            "where dp.division.id = ?1 and " +
            "dp.deletedFlag = false and " +
            "dp.division.deletedFlag = false"
  )
  Optional<DivisionPayroll> findOneActiveByDivisionId(UUID divisionId);

}
