package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Permission;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {

  @Query("select p from Permission p where p.deletedFlag = false")
  Page<Permission> findAndPageActive(Pageable pageable);

  @Query("select p from Permission p where p.role.id = ?1 and p.apiPath.value = ?2 and p.deletedFlag = false")
  List<Permission> findAllActiveByRoleIdAndApiPath(UUID roleId, String apiPath);

  @Query("select p from Permission p where p.role.id = ?1 and p.deletedFlag = false")
  List<Permission> findAllActiveByRoleId(UUID roleId);

  @Query("select p from Permission p where p.role.id = ?1 and p.deletedFlag = false")
  Page<Permission> findAndPageActiveByRoleId(UUID roleId, Pageable pageable);

}
