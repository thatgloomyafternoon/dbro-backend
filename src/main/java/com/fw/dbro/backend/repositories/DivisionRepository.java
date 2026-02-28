package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Division;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DivisionRepository extends JpaRepository<Division, UUID> {

  @Query(value = "select d from Division d where d.id = ?1 and d.deletedFlag = false")
  Optional<Division> findOneActiveById(UUID id);

  @Query(value = "select d from Division d where d.rootFlag = true and d.deletedFlag = false")
  List<Division> findAllActiveRootDivision();

  @Query(value = "select d from Division d where d.id not in (select ds.childDivision.id from DivisionStructure ds where ds.deletedFlag = false) and d.rootFlag = false and d.deletedFlag = false")
  List<Division> findAllActiveChildableDivision();

}
