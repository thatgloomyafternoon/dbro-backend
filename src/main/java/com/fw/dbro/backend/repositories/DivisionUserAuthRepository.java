package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Division;
import com.fw.dbro.backend.entities.DivisionUserAuth;
import com.fw.dbro.backend.entities.UserAuth;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DivisionUserAuthRepository extends JpaRepository<DivisionUserAuth, UUID> {

  /**
   * the call to
   * 'divisionUserAuthRepository.findOneActiveDivisionByUserAuthId'
   * must be done only by clock-in account, not user account,
   * to avoid error in the case of the user account calling the method
   * is registered to more than one outlet/division
   */
  @Query(value = "select dua.division from DivisionUserAuth dua where dua.userAuth.id = ?1 and dua.deletedFlag = false and dua.division.deletedFlag = false and dua.userAuth.deletedFlag = false")
  Optional<Division> findOneActiveDivisionByUserAuthId(UUID userAuthId);

  @Query(value = "select dua.userAuth from DivisionUserAuth dua where dua.division.id = ?1 and dua.deletedFlag = false")
  List<UserAuth> findAllActiveUsersInOneDivision(UUID divisionId);

  @Query(value = "select dua from DivisionUserAuth dua where dua.userAuth.id = ?1 and dua.deletedFlag = false")
  List<DivisionUserAuth> findAllActiveByUserAuthId(UUID userAuthId);

  @Query(value = "select dua from DivisionUserAuth dua where dua.division.id = ?1 and dua.userAuth.id = ?2 and dua.deletedFlag = false and dua.division.deletedFlag = false and dua.userAuth.deletedFlag = false")
  Optional<DivisionUserAuth> findOneActiveByDivisionIdAndUserAuthId(UUID divisionId, UUID userAuthId);

  @Query(value = "select dua from DivisionUserAuth dua where dua.deletedFlag = true")
  List<DivisionUserAuth> findAllDeleted();

}
