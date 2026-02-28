package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Division;
import com.fw.dbro.backend.entities.DivisionStructure;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DivisionStructureRepository extends JpaRepository<DivisionStructure, UUID> {

  @Query(value = "select ds.childDivision from DivisionStructure ds where ds.parentDivision.id = ?1 and ds.deletedFlag = false and ds.childDivision.deletedFlag = false")
  List<Division> findAllActiveChildFromOneDivision(UUID id);

  @Query(value = "select ds from DivisionStructure ds where ds.childDivision.id = ?1 and ds.deletedFlag = false")
  List<DivisionStructure> findAllActiveRelationByChildDivisionId(UUID divisionId);

}
