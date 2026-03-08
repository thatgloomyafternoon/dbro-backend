package com.fw.dbro.backend.utils;

import com.fw.dbro.backend.dto.ClockInUserDTO;
import com.fw.dbro.backend.dto.DivisionDropdownDTO;
import com.fw.dbro.backend.dto.DivisionListingDTO;
import com.fw.dbro.backend.dto.DivisionPayrollListingDTO;
import com.fw.dbro.backend.dto.MyPermissionDTO;
import com.fw.dbro.backend.dto.PeriodDropdownDTO;
import com.fw.dbro.backend.dto.PermissionDTO;
import com.fw.dbro.backend.dto.RoleDropdownDTO;
import com.fw.dbro.backend.dto.SysconfigDTO;
import com.fw.dbro.backend.dto.SysconfigTypeDTO;
import com.fw.dbro.backend.dto.UserAttendanceListingDTO;
import com.fw.dbro.backend.dto.UserClockInDataDTO;
import com.fw.dbro.backend.dto.UserDropdownDTO;
import com.fw.dbro.backend.dto.UserListingResultSet;
import com.fw.dbro.backend.dto.resultset.IUserListingResultSet;
import com.fw.dbro.backend.dto.DivisionUserListingDTO;
import com.fw.dbro.backend.dto.DivisionUserPayrollListingDTO;
import com.fw.dbro.backend.entities.ClockIn;
import com.fw.dbro.backend.entities.Division;
import com.fw.dbro.backend.entities.DivisionPayroll;
import com.fw.dbro.backend.entities.DivisionUserAuth;
import com.fw.dbro.backend.entities.Permission;
import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.entities.SysconfigType;
import com.fw.dbro.backend.entities.UserAttendance;
import com.fw.dbro.backend.entities.UserAuth;
import com.fw.dbro.backend.entities.UserPayroll;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import static com.fw.dbro.backend.constants.SystemConstants.DATE_PATTERN;
import static com.fw.dbro.backend.constants.SystemConstants.DATE_TIME_PATTERN;
import static com.fw.dbro.backend.constants.SystemConstants.ZONE_ID_ASIA_JAKARTA;
import static com.fw.dbro.backend.constants.SystemConstants.ZONE_ID_UTC;

@Component
public class MapperUtil {

  /**
   *
   */
  public SysconfigDTO mapSysconfigToSysconfigDTO(Sysconfig sysconfig) {
    SysconfigDTO dto = new SysconfigDTO();
    dto.setId(sysconfig.getId());
    dto.setCreatedDate(sysconfig.getCreatedDate());
    dto.setCreatedBy(sysconfig.getCreatedBy());
    dto.setLastModifiedDate(sysconfig.getLastModifiedDate());
    dto.setLastModifiedBy(sysconfig.getLastModifiedBy());
    dto.setKey(sysconfig.getKey());
    dto.setValue(sysconfig.getValue());
    return dto;
  }

  /**
   *
   */
  public SysconfigTypeDTO mapSysconfigTypeToSysconfigTypeDTO(SysconfigType sysconfigType) {
    SysconfigTypeDTO dto = new SysconfigTypeDTO();
    dto.setId(sysconfigType.getId());
    dto.setCreatedDate(sysconfigType.getCreatedDate());
    dto.setCreatedBy(sysconfigType.getCreatedBy());
    dto.setLastModifiedDate(sysconfigType.getLastModifiedDate());
    dto.setLastModifiedBy(sysconfigType.getLastModifiedBy());
    dto.setName(sysconfigType.getName());
    return dto;
  }

  /**
   *
   */
  public MyPermissionDTO mapPermissionToMyPermissionDTO(Permission permission) {
    MyPermissionDTO dto = new MyPermissionDTO();
    dto.setApiPathKey(permission.getApiPath().getKey());
    dto.setApiPathValue(permission.getApiPath().getValue());
    return dto;
  }

  /**
   *
   */
  public PermissionDTO mapPermissionToPermissionDTO(Permission permission) {
    PermissionDTO dto = new PermissionDTO();
    dto.setId(permission.getId());
    dto.setCreatedDate(permission.getCreatedDate());
    dto.setCreatedBy(permission.getCreatedBy());
    dto.setLastModifiedDate(permission.getLastModifiedDate());
    dto.setLastModifiedBy(permission.getLastModifiedBy());
    dto.setRole(permission.getRole().getValue());
    dto.setApiPathId(permission.getApiPath().getId());
    dto.setApiPathKey(permission.getApiPath().getKey());
    dto.setApiPathValue(permission.getApiPath().getValue());
    return dto;
  }

  /**
   *
   */
  public ClockInUserDTO mapDivisionUserAuthToClockInUserDTO(DivisionUserAuth divisionUserAuth) {
    ClockInUserDTO dto = new ClockInUserDTO();
    dto.setUserAuthId(divisionUserAuth.getUserAuth().getId());
    dto.setName(divisionUserAuth.getUserAuth().getUserInfo().getName());
    return dto;
  }

  /**
   *
   */
  public DivisionListingDTO mapDivisionToDivisionListingDTO(Division division) {
    DivisionListingDTO dto = new DivisionListingDTO();
    dto.setId(division.getId());
    dto.setCreatedDate(division.getCreatedDate().withZoneSameInstant(ZoneId.of(ZONE_ID_ASIA_JAKARTA)).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
    dto.setCreatedBy(division.getCreatedBy());
    dto.setName(division.getName());
    return dto;
  }

  /**
   *
   */
  public DivisionPayrollListingDTO mapDivisionPayrollToDivisionPayrollListingDTO(DivisionPayroll divisionPayroll) {
    DivisionPayrollListingDTO dto = new DivisionPayrollListingDTO();
    dto.setId(divisionPayroll.getDivision().getId());
    dto.setCreatedDate(divisionPayroll.getDivision().getCreatedDate().withZoneSameInstant(ZoneId.of(ZONE_ID_ASIA_JAKARTA)).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
    dto.setCreatedBy(divisionPayroll.getDivision().getCreatedBy());
    dto.setLastModifiedDate(divisionPayroll.getLastModifiedDate().withZoneSameInstant(ZoneId.of(ZONE_ID_ASIA_JAKARTA)).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
    dto.setLastModifiedBy(divisionPayroll.getLastModifiedBy());
    dto.setName(divisionPayroll.getDivision().getName());
    dto.setJumlahBonusHarian(divisionPayroll.getJumlahBonusHarian());
    dto.setJumlahBonusKawaka(divisionPayroll.getJumlahBonusKawaka());
    return dto;
  }

  /**
   *
   */
  public DivisionUserListingDTO mapUserAuthToDivisionUserListingDTO(UserAuth userAuth) {
    DivisionUserListingDTO dto = new DivisionUserListingDTO();
    dto.setId(userAuth.getId());
    dto.setCreatedDate(userAuth.getCreatedDate());
    dto.setCreatedBy(userAuth.getCreatedBy());
    dto.setEmail(userAuth.getEmail());
    dto.setName(userAuth.getUserInfo().getName());
    dto.setRole(userAuth.getUserInfo().getRole().getValue());
    return dto;
  }

  /**
   *
   */
  public DivisionDropdownDTO mapDivisionToDivisionDropdownDTO(Division division) {
    DivisionDropdownDTO dto = new DivisionDropdownDTO();
    dto.setId(division.getId());
    dto.setName(division.getName());
    return dto;
  }

  /**
   *
   */
  public UserDropdownDTO mapUserAuthToUserDropdownDTO(UserAuth userAuth) {
    UserDropdownDTO dto = new UserDropdownDTO();
    dto.setId(userAuth.getId());
    dto.setName(userAuth.getUserInfo().getName());
    return dto;
  }

  /**
   *
   */
  public RoleDropdownDTO mapSysconfigToRoleDropdownDTO(Sysconfig sysconfig) {
    RoleDropdownDTO dto = new RoleDropdownDTO();
    dto.setRoleId(sysconfig.getId());
    dto.setName(sysconfig.getValue());
    return dto;
  }

  /**
   *
   */
  public PeriodDropdownDTO mapSysconfigToPeriodDropdownDTO(Sysconfig sysconfig) {
    PeriodDropdownDTO dto = new PeriodDropdownDTO();
    dto.setPeriodId(sysconfig.getId());
    dto.setKey(sysconfig.getKey());
    dto.setValue(sysconfig.getValue());
    return dto;
  }

  /**
   *
   */
  public UserClockInDataDTO mapClockInToUserClockInDataDTO(ClockIn clockIn) {
    UserClockInDataDTO dto = new UserClockInDataDTO();
    dto.setDivisionName(clockIn.getDivisionUserAuth().getDivision().getName());
    dto.setClockInDate(clockIn.getCreatedDate().withZoneSameInstant(ZoneId.of(ZONE_ID_ASIA_JAKARTA)).format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
    dto.setClockType(clockIn.getClockType().getValue());
    dto.setLateFlag(clockIn.getLateFlag());
    return dto;
  }

  /**
   *
   */
  public DivisionUserPayrollListingDTO mapUserPayrollToDivisionUserPayrollListingDTO(UserPayroll userPayroll) {
    DivisionUserPayrollListingDTO dto = new DivisionUserPayrollListingDTO();
    dto.setUserAuthId(userPayroll.getUserAuth().getId());
    dto.setUserName(userPayroll.getUserAuth().getUserInfo().getName());
    dto.setJoinDate(userPayroll.getUserAuth().getUserInfo().getJoinDate().withZoneSameInstant(ZoneId.of(ZONE_ID_ASIA_JAKARTA)).format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    dto.setGajiPokok(userPayroll.getGajiPokok());
    dto.setUangMakan(userPayroll.getUangMakan());
    dto.setPotonganLainLain(userPayroll.getPotonganLainLain());
    return dto;
  }

  /**
   *
   */
  public UserAttendanceListingDTO mapUserAttendanceToUserAttendanceListingDTO(UserAttendance userAttendance) {
    UserAttendanceListingDTO dto = new UserAttendanceListingDTO();
    dto.setUserName(userAttendance.getDivisionUserAuth().getUserAuth().getUserInfo().getName());
    dto.setDivisionName(userAttendance.getDivisionUserAuth().getDivision().getName());
    dto.setMk(userAttendance.getCountMK());
    dto.setLs(userAttendance.getCountLS());
    dto.setLn(userAttendance.getCountLN());
    dto.setSkd(userAttendance.getCountSKD());
    dto.setCt(userAttendance.getCountCT());
    dto.setDisp(userAttendance.getCountDISP());
    dto.setOt(userAttendance.getCountOT());
    return dto;
  }

  /**
   *
   */
  public UserListingResultSet mapIUserListingResultSetToUserListingResultSet(IUserListingResultSet resultSet) {
    UserListingResultSet dto = new UserListingResultSet();
    dto.setId(resultSet.getId());
    dto.setName(resultSet.getName());
    LocalDate jd = resultSet.getJoinDate();
    ZonedDateTime zdtUtc = ZonedDateTime.of(jd.getYear(), jd.getMonthValue(), jd.getDayOfMonth(), 0, 0, 0, 0, ZoneId.of(ZONE_ID_UTC));
    ZonedDateTime zdtLocal = zdtUtc.withZoneSameInstant(ZoneId.of(ZONE_ID_ASIA_JAKARTA));
    dto.setJoinDate(zdtLocal.format(DateTimeFormatter.ofPattern(DATE_PATTERN)));
    dto.setCreatedBy(resultSet.getCreatedBy());
    dto.setRole(resultSet.getRole());
    dto.setDivision(resultSet.getDivision());
    return dto;
  }

}
