package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Sysconfig;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysconfigRepository extends JpaRepository<Sysconfig, UUID> {

  @Query(value = "select s from Sysconfig s where s.sysconfigType.name = ?1 and s.deletedFlag = false")
  List<Sysconfig> findAllActiveByType(String type);

  @Query(value = "select s from Sysconfig s where s.deletedFlag = false")
  Page<Sysconfig> findAndPageActive(Pageable pageable);

  @Query(value = "select s from Sysconfig s where s.sysconfigType.name = ?1 and s.deletedFlag = false")
  Page<Sysconfig> findAndPageActiveByType(String type, Pageable pageable);

  @Query(value = "select s from Sysconfig s where s.key = ?1 and s.deletedFlag = false")
  Optional<Sysconfig> findOneActiveByKey(String key);

  @Query(value = "select s from Sysconfig s where s.id = ?1 and s.deletedFlag = false")
  Optional<Sysconfig> findOneActiveById(UUID id);

}
