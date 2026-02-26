package com.fw.dbro.backend.web.api;

import com.fw.dbro.backend.constants.ApiPaths;
import com.fw.dbro.backend.services.ClockInService;
import java.time.LocalDate;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API + ApiPaths.CLOCK_IN)
public class ClockInController {

  private ClockInService clockInService;

  public ClockInController(ClockInService clockInService) {
    this.clockInService = clockInService;
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_BY_USER)
  public ResponseEntity<?> listByUser(@RequestParam(name = "userAuthId", required = true) UUID userAuthId) {
    return ResponseEntity.ok().body(clockInService.getClockInDataByUserAuthId(userAuthId));
  }

  /**
   *
   */
  @GetMapping(ApiPaths.FILTER_BY_USER_DATE)
  public ResponseEntity<?> filterByUserDate(
    @RequestParam(name = "userAuthId", required = true) UUID userAuthId,
    @RequestParam(name = "startDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
    @RequestParam(name = "endDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
  ) {
    return ResponseEntity.ok().body(clockInService.getClockInDataByUserAuthIdAndDateRange(userAuthId, startDate, endDate));
  }

}
