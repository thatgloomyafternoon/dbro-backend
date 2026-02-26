package com.fw.dbro.backend.utils;

import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.repositories.SysconfigRepository;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.stereotype.Component;
import static com.fw.dbro.backend.constants.SystemConstants.*;

@Component
public class DateTimeUtil {

  private SysconfigRepository sysconfigRepository;

  public DateTimeUtil(SysconfigRepository sysconfigRepository) {
    this.sysconfigRepository = sysconfigRepository;
  }

  /**
   *
   */
  public boolean isInNationalHoliday(ZonedDateTime zdt) {
    boolean retval = false;
    List<Sysconfig> ln = sysconfigRepository.findAllActiveByType(LIBUR_NASIONAL);
    for(Sysconfig sysconfig : ln) {
      int year = Integer.parseInt(sysconfig.getValue().split("\\-")[0]);
      int month = Integer.parseInt(sysconfig.getValue().split("\\-")[1]);
      int day = Integer.parseInt(sysconfig.getValue().split("\\-")[2]);
      if(zdt.getYear() == year && zdt.getMonthValue() == month && zdt.getDayOfMonth() == day) {
        retval = true;
        break;
      }
    }
    return retval;
  }

  /**
   * return 0 --> not late
   * return 1 --> late
   * return 2 --> clock-in not available
   */
  public int determineClockInTimeResult(ZonedDateTime clockInTime) {
    List<Sysconfig> clockInShifts = sysconfigRepository.findAllActiveByType(CLOCK_IN_SHIFT);
    int offset = Integer.parseInt(sysconfigRepository.findAllActiveByType(CLOCK_IN_OFFSET).get(0).getValue());
    int year = clockInTime.getYear();
    int month = clockInTime.getMonthValue();
    int day = clockInTime.getDayOfMonth();
    for(Sysconfig clockInShift : clockInShifts) {
      int hour = Integer.parseInt(clockInShift.getValue());
      ZonedDateTime lowerLimit = ZonedDateTime.of(year, month, day, hour, 0, 0, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA)).minusMinutes((long)(offset));
      ZonedDateTime onTime = ZonedDateTime.of(year, month, day, hour, 0, 0, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA));
      ZonedDateTime upperLimit = ZonedDateTime.of(year, month, day, hour, 0, 0, 0, ZoneId.of(ZONE_ID_ASIA_JAKARTA)).plusMinutes((long)(offset));
      if(lowerLimit.isBefore(clockInTime) && (onTime.isAfter(clockInTime) || onTime.isEqual(clockInTime))) {
        return 0;
      } else if(onTime.isBefore(clockInTime) && upperLimit.isAfter(clockInTime)) {
        return 1;
      }
    }
    return 2;
  }

}
