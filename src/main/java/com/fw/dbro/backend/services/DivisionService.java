package com.fw.dbro.backend.services;

import com.fw.dbro.backend.dto.ClockInUserDTO;
import com.fw.dbro.backend.dto.DivisionDropdownDTO;
import com.fw.dbro.backend.dto.DivisionListingDTO;
import com.fw.dbro.backend.dto.DivisionPayrollListingDTO;
import com.fw.dbro.backend.dto.JwtClaimDTO;
import com.fw.dbro.backend.dto.DivisionUserListingDTO;
import com.fw.dbro.backend.entities.Division;
import com.fw.dbro.backend.entities.DivisionPayroll;
import com.fw.dbro.backend.entities.DivisionStructure;
import com.fw.dbro.backend.entities.DivisionUserAuth;
import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.entities.UserAuth;
import com.fw.dbro.backend.repositories.DivisionPayrollRepository;
import com.fw.dbro.backend.repositories.DivisionRepository;
import com.fw.dbro.backend.repositories.DivisionStructureRepository;
import com.fw.dbro.backend.repositories.DivisionUserAuthExtRepository;
import com.fw.dbro.backend.repositories.DivisionUserAuthRepository;
import com.fw.dbro.backend.repositories.SysconfigExtRepository;
import com.fw.dbro.backend.repositories.UserAuthRepository;
import com.fw.dbro.backend.utils.MapperUtil;
import com.fw.dbro.backend.web.responses.ClockInUserListingResponse;
import com.fw.dbro.backend.web.responses.DivisionListingResponse;
import com.fw.dbro.backend.web.responses.DivisionPayrollListingResponse;
import com.fw.dbro.backend.web.responses.DivisionUserListingResponse;
import com.fw.dbro.backend.web.responses.SimpleIdResponse;
import com.fw.dbro.backend.web.responses.SimpleMessageResponse;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.SystemConstants.*;

@Service
public class DivisionService {

  private MapperUtil mapperUtil;
  private DivisionUserAuthRepository divisionUserAuthRepository;
  private DivisionUserAuthExtRepository divisionUserAuthExtRepository;
  private DivisionRepository divisionRepository;
  private DivisionStructureRepository divisionStructureRepository;
  private DivisionPayrollRepository divisionPayrollRepository;
  private UserAuthRepository userAuthRepository;
  private SysconfigExtRepository sysconfigExtRepository;

  public DivisionService(
    MapperUtil mapperUtil,
    DivisionUserAuthRepository divisionUserAuthRepository,
    DivisionUserAuthExtRepository divisionUserAuthExtRepository,
    DivisionRepository divisionRepository,
    DivisionStructureRepository divisionStructureRepository,
    DivisionPayrollRepository divisionPayrollRepository,
    UserAuthRepository userAuthRepository,
    SysconfigExtRepository sysconfigExtRepository
  ) {
    this.mapperUtil = mapperUtil;
    this.divisionUserAuthRepository = divisionUserAuthRepository;
    this.divisionUserAuthExtRepository = divisionUserAuthExtRepository;
    this.divisionRepository = divisionRepository;
    this.divisionStructureRepository = divisionStructureRepository;
    this.divisionPayrollRepository = divisionPayrollRepository;
    this.userAuthRepository = userAuthRepository;
    this.sysconfigExtRepository = sysconfigExtRepository;
  }

  /**
   * the call to
   * 'divisionUserAuthRepository.findOneActiveDivisionByUserAuthId'
   * must be done only by outlet account, not user account,
   * to avoid mapping error if the account calling the method
   * is registered to more than one outlet/division
   */
  public ClockInUserListingResponse getListClockInUserMyDivision() {
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    Optional<Division> optDivision = divisionUserAuthRepository.findOneActiveDivisionByUserAuthId(jwtClaimDTO.getUserAuthId());
    if(!optDivision.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    List<ClockInUserDTO> clockInUserDTOs = optDivision.get()
      .getDivisionUserAuths()
      .stream()
      .filter(dua -> !dua.getUserAuth().getUserInfo().getRole().getKey().equals(CLOCK_IN_ROLE))
      .map(mapperUtil::mapDivisionUserAuthToClockInUserDTO)
      .sorted(Comparator.comparing(ClockInUserDTO::getName))
      .collect(Collectors.toList());
    return new ClockInUserListingResponse((long)(clockInUserDTOs.size()), optDivision.get().getId(), clockInUserDTOs);
  }

  /**
   *
   */
  public DivisionListingResponse getListRootDivision() {
    List<DivisionListingDTO> divisionListingDTOs = divisionRepository.findAllActiveRootDivision()
      .stream()
      .map(mapperUtil::mapDivisionToDivisionListingDTO)
      .sorted(Comparator.comparing(DivisionListingDTO::getName))
      .collect(Collectors.toList());
    return new DivisionListingResponse((long)(divisionListingDTOs.size()), divisionListingDTOs);
  }

  /**
   *
   */
  public DivisionPayrollListingResponse getListOutlet() {
    Optional<Sysconfig> optPeriod = sysconfigExtRepository.findOneActiveCurrentPeriod();
    if(!optPeriod.isPresent()) {
      throw new IllegalArgumentException(PERIOD_NOT_EXISTS);
    }
    List<DivisionPayrollListingDTO> divisionPayrollListingDTOs = divisionPayrollRepository.findAllActiveByPeriodId(optPeriod.get().getId())
      .stream()
      .map(mapperUtil::mapDivisionPayrollToDivisionPayrollListingDTO)
      .sorted(Comparator.comparing(DivisionPayrollListingDTO::getName))
      .collect(Collectors.toList());
    return new DivisionPayrollListingResponse((long)(divisionPayrollListingDTOs.size()), divisionPayrollListingDTOs);
  }

  /**
   *
   */
  public DivisionListingResponse getListChildFromOneDivision(UUID divisionId) {
    List<DivisionListingDTO> divisionListingDTOs = divisionStructureRepository.findAllActiveChildFromOneDivision(divisionId)
      .stream()
      .map(mapperUtil::mapDivisionToDivisionListingDTO)
      .sorted(Comparator.comparing(DivisionListingDTO::getName))
      .collect(Collectors.toList());
    return new DivisionListingResponse((long)(divisionListingDTOs.size()), divisionListingDTOs);
  }

  /**
   *
   */
  public DivisionUserListingResponse getAllUsersFromOneDivision(UUID divisionId) {
    List<DivisionUserListingDTO> divisionUserListingDTOs = divisionUserAuthRepository.findAllActiveUsersInOneDivision(divisionId)
      .stream()
      .map(mapperUtil::mapUserAuthToDivisionUserListingDTO)
      .sorted(Comparator.comparing(DivisionUserListingDTO::getName))
      .collect(Collectors.toList());
    return new DivisionUserListingResponse((long)(divisionUserListingDTOs.size()), divisionUserListingDTOs);
  }

  /**
   *
   */
  public SimpleIdResponse createDivision(String name, Boolean rootFlag, Boolean outletFlag) {
    if(rootFlag && outletFlag) {
      throw new IllegalArgumentException(OUTLET_CANNOT_BE_ROOT_MESSAGE);
    }
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    Division division = new Division();
    division.setCreatedBy(jwtClaimDTO.getEmail());
    division.setLastModifiedBy(jwtClaimDTO.getEmail());
    division.setRootFlag(rootFlag);
    division.setName(name);
    division = divisionRepository.save(division);
    if(outletFlag) {
      Sysconfig period = sysconfigExtRepository.findOneActiveCurrentPeriod().get();
      DivisionPayroll divisionPayroll = new DivisionPayroll();
      divisionPayroll.setCreatedBy(jwtClaimDTO.getEmail());
      divisionPayroll.setLastModifiedBy(jwtClaimDTO.getEmail());
      divisionPayroll.setPeriod(period);
      divisionPayroll.setDivision(division);
      divisionPayroll.setJumlahBonusHarian(new BigDecimal(0));
      divisionPayroll.setJumlahBonusKawaka(new BigDecimal(0));
      divisionPayrollRepository.save(divisionPayroll);
    }
    return new SimpleIdResponse(division.getId());
  }

  /**
   * This method, despite its name, should only be used for
   * retrieving Divisions which are:
   * - not a root division
   * - not having parent
   */
  public List<DivisionDropdownDTO> getDivisionDropdown() {
    return divisionRepository.findAllActiveChildableDivision()
      .stream()
      .map(mapperUtil::mapDivisionToDivisionDropdownDTO)
      .sorted(Comparator.comparing(DivisionDropdownDTO::getName))
      .collect(Collectors.toList());
  }

  /**
   *
   */
  public SimpleIdResponse createDivisionRelation(UUID parentDivId, UUID childDivId) {
    /* check if child already has parent */
    List<DivisionStructure> divisionStructures = divisionStructureRepository.findAllActiveRelationByChildDivisionId(childDivId);
    if(divisionStructures.size() > 0) {
      throw new IllegalArgumentException(DIVISION_ALREADY_HAS_PARENT);
    }
    /* check if parent and child division with the given id actually exist */
    Optional<Division> optParent = divisionRepository.findOneActiveById(parentDivId);
    Optional<Division> optChild = divisionRepository.findOneActiveById(childDivId);
    if(!optParent.isPresent() || !optChild.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    /* check if child is actually a root division */
    if(optChild.get().getRootFlag()) {
      throw new IllegalArgumentException(CHILD_DIVISION_IS_ROOT);
    }
    /* create new relation */
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    DivisionStructure divisionStructure = new DivisionStructure();
    divisionStructure.setCreatedBy(jwtClaimDTO.getEmail());
    divisionStructure.setLastModifiedBy(jwtClaimDTO.getEmail());
    divisionStructure.setParentDivision(optParent.get());
    divisionStructure.setChildDivision(optChild.get());
    divisionStructure = divisionStructureRepository.save(divisionStructure);
    return new SimpleIdResponse(divisionStructure.getId());
  }

  /**
   *
   */
  public SimpleIdResponse addUserToDivision(UUID divisionId, UUID userAuthId) {
    /* check if user actually exists */
    Optional<UserAuth> optUserAuth = userAuthRepository.findOneActiveById(userAuthId);
    if(!optUserAuth.isPresent()) {
      throw new IllegalArgumentException(USER_NOT_FOUND);
    }
    /* check if division exists */
    Optional<Division> optDivision = divisionRepository.findOneActiveById(divisionId);
    if(!optDivision.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    /* check if user info exists */
    if(optUserAuth.get().getUserInfo() == null) {
      throw new IllegalArgumentException(CORRESPONDING_USER_INFO_NOT_FOUND);
    }
    /* check if user has clock-in role and already registered to a division */
    List<DivisionUserAuth> divisionUserAuths = divisionUserAuthRepository.findAllActiveByUserAuthId(userAuthId);
    if(optUserAuth.get().getUserInfo().getRole().getKey().equals(CLOCK_IN_ROLE) && divisionUserAuths.size() > 0) {
      throw new IllegalArgumentException(CLOCK_IN_USER_NOT_AVAILABLE);
    }
    /* check if user already registered to the division */
    Optional<DivisionUserAuth> optDivisionUserAuth = divisionUserAuthRepository.findOneActiveByDivisionIdAndUserAuthId(divisionId, userAuthId);
    if(optDivisionUserAuth.isPresent()) {
      throw new IllegalArgumentException("User already in the division");
    }
    /* add user to division */
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    DivisionUserAuth divisionUserAuth = new DivisionUserAuth();
    divisionUserAuth.setCreatedBy(jwtClaimDTO.getEmail());
    divisionUserAuth.setLastModifiedBy(jwtClaimDTO.getEmail());
    divisionUserAuth.setDivision(optDivision.get());
    divisionUserAuth.setUserAuth(optUserAuth.get());
    divisionUserAuth = divisionUserAuthRepository.save(divisionUserAuth);
    return new SimpleIdResponse(divisionUserAuth.getId());
  }

  /**
   *
   */
  public SimpleMessageResponse removeUserFromDivision(UUID divisionId, UUID userAuthId) {
    /* check if user actually exists */
    Optional<UserAuth> optUserAuth = userAuthRepository.findOneActiveById(userAuthId);
    if(!optUserAuth.isPresent()) {
      throw new IllegalArgumentException(USER_NOT_FOUND);
    }
    /* check if division exists */
    Optional<Division> optDivision = divisionRepository.findOneActiveById(divisionId);
    if(!optDivision.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    /* check if user info exists */
    if(optUserAuth.get().getUserInfo() == null) {
      throw new IllegalArgumentException(CORRESPONDING_USER_INFO_NOT_FOUND);
    }
    /* check if user already registered to the division */
    Optional<DivisionUserAuth> optDivisionUserAuth = divisionUserAuthRepository.findOneActiveByDivisionIdAndUserAuthId(divisionId, userAuthId);
    if(!optDivisionUserAuth.isPresent()) {
      throw new IllegalArgumentException(DIVISION_USER_AUTH_NOT_FOUND_MESSAGE);
    }
    /* remove user from division */
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    divisionUserAuthExtRepository.softDelete(optDivisionUserAuth.get(), jwtClaimDTO.getEmail());
    return new SimpleMessageResponse(OK);
  }

  /**
   *
   */
  public SimpleIdResponse updatePayrollInfo(UUID divisionId, BigDecimal jumlahBonusHarian, BigDecimal jumlahBonusKawaka) {
    /* check if division exists */
    Optional<Division> optDivision = divisionRepository.findOneActiveById(divisionId);
    if(!optDivision.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    /* check if division payroll actually exists */
    Optional<DivisionPayroll> optDivisionPayroll = divisionPayrollRepository.findOneActiveByDivisionId(divisionId);
    if(!optDivisionPayroll.isPresent()) {
      throw new IllegalArgumentException(DIVISION_PAYROLL_NOT_FOUND);
    }
    /* soft delete the old one first */
    JwtClaimDTO jwtClaimDTO = (JwtClaimDTO)((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getDetails();
    DivisionPayroll oldDivisionPayroll = optDivisionPayroll.get();
    oldDivisionPayroll.setDeletedDate(ZonedDateTime.now());
    oldDivisionPayroll.setDeletedBy(jwtClaimDTO.getEmail());
    oldDivisionPayroll.setDeletedFlag(true);
    divisionPayrollRepository.save(oldDivisionPayroll);
    /* create new one */
    Sysconfig period = sysconfigExtRepository.findOneActiveCurrentPeriod().get();
    DivisionPayroll newDivisionPayroll = new DivisionPayroll();
    newDivisionPayroll.setCreatedBy(jwtClaimDTO.getEmail());
    newDivisionPayroll.setLastModifiedBy(jwtClaimDTO.getEmail());
    newDivisionPayroll.setPeriod(period);
    newDivisionPayroll.setDivision(optDivision.get());
    newDivisionPayroll.setJumlahBonusHarian(jumlahBonusHarian);
    newDivisionPayroll.setJumlahBonusKawaka(jumlahBonusKawaka);
    newDivisionPayroll = divisionPayrollRepository.save(newDivisionPayroll);
    return new SimpleIdResponse(newDivisionPayroll.getId());
  }

}
