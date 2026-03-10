package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Permission;
import java.time.ZonedDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class PermissionExtRepository {

  private PermissionRepository permissionRepository;

  public PermissionExtRepository(PermissionRepository permissionRepository) {
    this.permissionRepository = permissionRepository;
  }

  /**
   *
   */
  public void softDelete(Permission permission, String deletedBy) {
    permission.setDeletedDate(ZonedDateTime.now());
    permission.setDeletedBy(deletedBy);
    permission.setDeletedFlag(true);
    permissionRepository.save(permission);
  }

}
