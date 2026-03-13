package com.fw.dbro.backend.services;

import com.fw.dbro.backend.dto.JwtClaimDTO;
import com.fw.dbro.backend.dto.PeriodDropdownDTO;
import com.fw.dbro.backend.dto.RoleDropdownDTO;
import com.fw.dbro.backend.dto.SysconfigDTO;
import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.entities.SysconfigType;
import com.fw.dbro.backend.repositories.SysconfigRepository;
import com.fw.dbro.backend.repositories.SysconfigTypeRepository;
import com.fw.dbro.backend.utils.MapperUtil;
import com.fw.dbro.backend.web.responses.SimpleIdResponse;
import com.fw.dbro.backend.web.responses.SysconfigListingResponse;
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
public class SysconfigService {

  private MapperUtil mapperUtil;
  private SysconfigTypeRepository sysconfigTypeRepository;
  private SysconfigRepository sysconfigRepository;

  public SysconfigService(MapperUtil mapperUtil, SysconfigTypeRepository sysconfigTypeRepository, SysconfigRepository sysconfigRepository) {
    this.mapperUtil = mapperUtil;
    this.sysconfigTypeRepository = sysconfigTypeRepository;
    this.sysconfigRepository = sysconfigRepository;
  }

  /**
   *
   */
  public SysconfigListingResponse getAllSysconfigsByType(int page, int size, String type) {
    List<SysconfigDTO> sysconfigDTOs = new ArrayList<>();
    Pageable pageable = PageRequest.of(page, size);
    Page<Sysconfig> sysconfigsPage;
    if(type == null) {
      sysconfigsPage = sysconfigRepository.findAndPageActive(pageable);
    } else {
      sysconfigsPage = sysconfigRepository.findAndPageActiveByType(type, pageable);
    }
    long totalCount = sysconfigsPage.getTotalElements();
    sysconfigDTOs = sysconfigsPage.getContent().stream()
      .map(mapperUtil::mapSysconfigToSysconfigDTO)
      .sorted(Comparator.comparing(SysconfigDTO::getCreatedDate).reversed())
      .collect(Collectors.toList());
    return new SysconfigListingResponse(totalCount, sysconfigDTOs);
  }

  /**
   *
   */
  public SimpleIdResponse createSysconfig(UUID sysconfigTypeId, String key, String value) {
    Optional<SysconfigType> optSysconfigType = sysconfigTypeRepository.findById(sysconfigTypeId);
    /* check if sysconfig type is valid and present */
    if(!optSysconfigType.isPresent()) {
      throw new IllegalArgumentException(SYSCONFIG_TYPE_NOT_FOUND);
    }
    /* check if sysconfig with the given key already exist */
    if(sysconfigRepository.findOneActiveByKey(key).isPresent()) {
      throw new IllegalArgumentException(SYSCONFIG_ALREADY_EXISTS);
    }
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    Sysconfig newSysconfig = new Sysconfig(optSysconfigType.get(), key, value);
    newSysconfig.setCreatedBy(jwtClaimDTO.getEmail());
    newSysconfig.setLastModifiedBy(jwtClaimDTO.getEmail());
    newSysconfig = sysconfigRepository.save(newSysconfig);
    return new SimpleIdResponse(newSysconfig.getId());
  }

  /**
   * This will retrieve all role except SYSTEM_MASTER
   */
  public List<RoleDropdownDTO> getRoleDropdown() {
    return sysconfigRepository.findAllActiveByType(ROLE)
      .stream()
      .filter(s -> !s.getKey().equals(SYSTEM_MASTER))
      .map(mapperUtil::mapSysconfigToRoleDropdownDTO)
      .sorted(Comparator.comparing(RoleDropdownDTO::getName))
      .collect(Collectors.toList());
  }

  /**
   *
   */
  public List<PeriodDropdownDTO> getPeriodDropdown() {
    return sysconfigRepository.findAllActiveByType(PERIOD)
      .stream()
      .map(mapperUtil::mapSysconfigToPeriodDropdownDTO)
      .sorted(Comparator.comparing(PeriodDropdownDTO::getKey))
      .collect(Collectors.toList());
  }

}
