package com.fw.dbro.backend.web.api;

import com.fw.dbro.backend.constants.ApiPaths;
import com.fw.dbro.backend.services.PermissionService;
import com.fw.dbro.backend.web.requests.PermissionCreateRequest;
import com.fw.dbro.backend.web.requests.PermissionDeleteRequest;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API + ApiPaths.PERMISSION)
public class PermissionController {

  private PermissionService permissionService;

  public PermissionController(PermissionService permissionService) {
    this.permissionService = permissionService;
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_MY_PERMISSION)
  public ResponseEntity<?> listMyPermission() {
    return ResponseEntity.ok().body(permissionService.getMyPermissions());
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST)
  public ResponseEntity<?> list(
    @RequestParam(name = "roleId", required = false) UUID roleId,
    @RequestParam(name = "page", required = true) int page,
    @RequestParam(name = "size", required = true) int size
  ) {
    return ResponseEntity.ok().body(permissionService.getAllPermissionsByRoleIdOptional(page, size, roleId));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.CREATE)
  public ResponseEntity<?> create(@Validated @RequestBody PermissionCreateRequest request) {
    return ResponseEntity.ok().body(permissionService.createPermission(request.getRoleId(), request.getApiPathId()));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.DELETE)
  public ResponseEntity<?> delete(@Validated @RequestBody PermissionDeleteRequest request) {
    return ResponseEntity.ok().body(permissionService.deletePermission(request.getPermissionId()));
  }

}
