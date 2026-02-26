package com.fw.dbro.backend.services;

import com.fw.dbro.backend.dto.UserClockInDataDTO;
import com.fw.dbro.backend.entities.UserAuth;
import com.fw.dbro.backend.repositories.ClockInRepository;
import com.fw.dbro.backend.repositories.UserAuthRepository;
import com.fw.dbro.backend.utils.MapperUtil;
import com.fw.dbro.backend.web.responses.UserClockInDataResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.SystemConstants.ZONE_ID_ASIA_JAKARTA;
import static com.fw.dbro.backend.constants.SystemConstants.ZONE_ID_UTC;

@Service
public class ClockInService {

  private MapperUtil mapperUtil;
  private UserAuthRepository userAuthRepository;
  private ClockInRepository clockInRepository;

  public ClockInService(
    MapperUtil mapperUtil,
    UserAuthRepository userAuthRepository,
    ClockInRepository clockInRepository
  ) {
    this.mapperUtil = mapperUtil;
    this.userAuthRepository = userAuthRepository;
    this.clockInRepository = clockInRepository;
  }

  /**
   *
   */
  public UserClockInDataResponse getClockInDataByUserAuthId(UUID userAuthId) {
    /* check UserAuth record */
    Optional<UserAuth> optUserAuth = userAuthRepository.findOneActiveById(userAuthId);
    if(!optUserAuth.isPresent()) {
      throw new IllegalArgumentException(USER_NOT_FOUND);
    }
    /* */
    List<UserClockInDataDTO> userClockInDataDTOs = clockInRepository.findAllByUserAuthIdOrderCreatedDateDesc(userAuthId)
      .stream()
      .map(mapperUtil::mapClockInToUserClockInDataDTO)
      .collect(Collectors.toList());
    return new UserClockInDataResponse(optUserAuth.get().getUserInfo().getName(), userClockInDataDTOs);
  }

  /**
   *
   */
  public UserClockInDataResponse getClockInDataByUserAuthIdAndDateRange(UUID userAuthId, LocalDate startDate, LocalDate endDate) {
    /* convert local date to zoned date time utc */
    ZonedDateTime startZdtLocal = ZonedDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), 0, 0, 0, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA));
    ZonedDateTime startZdtUtc = startZdtLocal.withZoneSameInstant(ZoneId.of(ZONE_ID_UTC));
    ZonedDateTime endZdtLocal = ZonedDateTime.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth(), 23, 59, 59, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA));
    ZonedDateTime endZdtUtc = endZdtLocal.withZoneSameInstant(ZoneId.of(ZONE_ID_UTC));
    /* check UserAuth record */
    Optional<UserAuth> optUserAuth = userAuthRepository.findOneActiveById(userAuthId);
    if(!optUserAuth.isPresent()) {
      throw new IllegalArgumentException(USER_NOT_FOUND);
    }
    /* */
    List<UserClockInDataDTO> userClockInDataDTOs = clockInRepository.findAllByUserAuthIdAndDateRange(userAuthId, startZdtUtc, endZdtUtc)
      .stream()
      .map(mapperUtil::mapClockInToUserClockInDataDTO)
      .collect(Collectors.toList());
    return new UserClockInDataResponse(optUserAuth.get().getUserInfo().getName(), userClockInDataDTOs);
  }

}
