package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.InvalidJwt;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvalidJwtRepository extends JpaRepository<InvalidJwt, UUID> {

  @Query("select ij from InvalidJwt ij where ij.jwt = ?1 and ij.deletedFlag = false")
  Optional<InvalidJwt> findOneActiveByJwt(String jwt);

}
