package com.fw.dbro.backend.services;

import com.fw.dbro.backend.dto.JwtClaimDTO;
import com.fw.dbro.backend.dto.MyPermissionDTO;
import com.fw.dbro.backend.dto.PermissionDTO;
import com.fw.dbro.backend.entities.Permission;
import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.repositories.PermissionExtRepository;
import com.fw.dbro.backend.repositories.PermissionRepository;
import com.fw.dbro.backend.repositories.SysconfigRepository;
import com.fw.dbro.backend.utils.MapperUtil;
import com.fw.dbro.backend.web.responses.PermissionListingResponse;
import com.fw.dbro.backend.web.responses.SimpleIdResponse;
import com.fw.dbro.backend.web.responses.SimpleMessageResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.SystemConstants.*;

@Service
public class PermissionService {

  private MapperUtil mapperUtil;
  private SysconfigRepository sysconfigRepository;
  private PermissionRepository permissionRepository;
  private PermissionExtRepository permissionExtRepository;

  public PermissionService(
    MapperUtil mapperUtil,
    SysconfigRepository sysconfigRepository,
    PermissionRepository permissionRepository,
    PermissionExtRepository permissionExtRepository
  ) {
    this.mapperUtil = mapperUtil;
    this.sysconfigRepository = sysconfigRepository;
    this.permissionRepository = permissionRepository;
    this.permissionExtRepository = permissionExtRepository;
  }

  /**
   *
   */
  public Boolean checkIfRoleAllowedForApiPath(UUID roleId, String apiPath) {
    List<Permission> permission = permissionRepository.findAllActiveByRoleIdAndApiPath(roleId, apiPath);
    if(permission.size() > 0) {
      return true;
    }
    return false;
  }

  /**
   *
   */
  public List<MyPermissionDTO> getMyPermissions() {
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    return permissionRepository.findAllActiveByRoleId(jwtClaimDTO.getRoleId())
      .stream()
      .map(mapperUtil::mapPermissionToMyPermissionDTO)
      .collect(Collectors.toList());
  }

  /**
   *
   */
  public PermissionListingResponse getAllPermissionsByRoleIdOptional(int page, int size, UUID roleId) {
    List<PermissionDTO> permissionDTOs = new ArrayList<>();
    Pageable pageable = PageRequest.of(page, size);
    Page<Permission> permissionsPage;
    if(roleId == null) {
      permissionsPage = permissionRepository.findAndPageActive(pageable);
    } else {
      permissionsPage = permissionRepository.findAndPageActiveByRoleId(roleId, pageable);
    }
    long totalCount = permissionsPage.getTotalElements();
    permissionDTOs = permissionsPage.getContent().stream()
      .map(mapperUtil::mapPermissionToPermissionDTO)
      .sorted(Comparator.comparing(PermissionDTO::getCreatedDate).reversed())
      .collect(Collectors.toList());
    return new PermissionListingResponse(totalCount, permissionDTOs);
  }

  /**
   * NOTE:
   * - roleId is equal to sysconfigId
   * - apiPathId is also equal to sysconfigId
   */
  public SimpleIdResponse createPermission(UUID roleId, UUID apiPathId) {
    Optional<Sysconfig> optRole = sysconfigRepository.findById(roleId);
    if(!optRole.isPresent()) {
      throw new IllegalArgumentException(ROLE_NOT_FOUND);
    }
    if(!optRole.get().getSysconfigType().getName().equals(ROLE)) {
      throw new IllegalArgumentException(ROLE_NOT_FOUND);
    }
    Optional<Sysconfig> optApiPath = sysconfigRepository.findById(apiPathId);
    if(!optApiPath.isPresent()) {
      throw new IllegalArgumentException(API_PATH_NOT_FOUND);
    }
    if(!optApiPath.get().getSysconfigType().getName().equals(API_PATH)) {
      throw new IllegalArgumentException(API_PATH_NOT_FOUND);
    }
    if(permissionRepository.findAllActiveByRoleIdAndApiPath(roleId, optApiPath.get().getValue()).size() > 0) {
      throw new IllegalArgumentException(PERMISSION_ALREADY_EXISTS);
    }
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    Permission newPermission = new Permission();
    newPermission.setCreatedBy(jwtClaimDTO.getEmail());
    newPermission.setLastModifiedBy(jwtClaimDTO.getEmail());
    newPermission.setRole(optRole.get());
    newPermission.setApiPath(optApiPath.get());
    newPermission = permissionRepository.save(newPermission);
    return new SimpleIdResponse(newPermission.getId());
  }

  /**
   *
   */
  public SimpleMessageResponse deletePermission(UUID permissionId) {
    Optional<Permission> optPermission = permissionRepository.findById(permissionId);
    if(!optPermission.isPresent()) {
      throw new IllegalArgumentException(PERMISSION_NOT_FOUND);
    }
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    permissionExtRepository.softDelete(optPermission.get(), jwtClaimDTO.getEmail());
    return new SimpleMessageResponse(OK);
  }

}
