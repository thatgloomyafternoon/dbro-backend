package com.fw.dbro.backend.repositories;

import com.fw.dbro.backend.entities.Sysconfig;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import static com.fw.dbro.backend.constants.SystemConstants.*;

@Repository
public class SysconfigExtRepository {

  private SysconfigRepository sysconfigRepository;

  public SysconfigExtRepository(SysconfigRepository sysconfigRepository) {
    this.sysconfigRepository = sysconfigRepository;
  }

  /**
   *
   */
  public Optional<Sysconfig> findOneActiveCurrentPeriod() {
    ZonedDateTime now = ZonedDateTime.now();
    List<Sysconfig> sysconfigs = sysconfigRepository.findAllActiveByType(PERIOD);
    for(Sysconfig sysconfig : sysconfigs) {
      String[] s = sysconfig.getValue().split("\\-");
      ZonedDateTime zdt1 = ZonedDateTime.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), 0, 0, 0, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA));
      ZonedDateTime zdt2 = ZonedDateTime.of(Integer.parseInt(s[3]), Integer.parseInt(s[4]), Integer.parseInt(s[5]), 23, 59, 59, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA));
      if(now.isAfter(zdt1) && now.isBefore(zdt2)) {
        return Optional.of(sysconfig);
      }
    }
    return Optional.empty();
  }

}
