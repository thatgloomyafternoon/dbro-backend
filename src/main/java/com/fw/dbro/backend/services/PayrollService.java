package com.fw.dbro.backend.services;

import com.fw.dbro.backend.dto.DivisionUserPayrollListingDTO;
import com.fw.dbro.backend.dto.ExportUserPayrollDTO;
import com.fw.dbro.backend.entities.Division;
import com.fw.dbro.backend.entities.DivisionPayroll;
import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.entities.UserAttendance;
import com.fw.dbro.backend.entities.UserInfo;
import com.fw.dbro.backend.entities.UserPayroll;
import com.fw.dbro.backend.repositories.DivisionRepository;
import com.fw.dbro.backend.repositories.SysconfigExtRepository;
import com.fw.dbro.backend.repositories.SysconfigRepository;
import com.fw.dbro.backend.repositories.UserAttendanceRepository;
import com.fw.dbro.backend.repositories.UserPayrollRepository;
import com.fw.dbro.backend.utils.MapperUtil;
import com.fw.dbro.backend.web.responses.DivisionUserPayrollListingResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;
import static com.fw.dbro.backend.constants.SystemConstants.*;

@Service
public class PayrollService {

  private MapperUtil mapperUtil;
  private DivisionRepository divisionRepository;
  private UserAttendanceRepository userAttendanceRepository;
  private SysconfigRepository sysconfigRepository;
  private UserPayrollRepository userPayrollRepository;
  private SysconfigExtRepository sysconfigExtRepository;
  private ExportExcelService exportExcelService;

  public PayrollService(
    MapperUtil mapperUtil,
    DivisionRepository divisionRepository,
    UserAttendanceRepository userAttendanceRepository,
    SysconfigRepository sysconfigRepository,
    UserPayrollRepository userPayrollRepository,
    SysconfigExtRepository sysconfigExtRepository,
    ExportExcelService exportExcelService
  ) {
    this.mapperUtil = mapperUtil;
    this.divisionRepository = divisionRepository;
    this.userAttendanceRepository = userAttendanceRepository;
    this.sysconfigRepository = sysconfigRepository;
    this.userPayrollRepository = userPayrollRepository;
    this.sysconfigExtRepository = sysconfigExtRepository;
    this.exportExcelService = exportExcelService;
  }

  /**
   *
   */
  public DivisionUserPayrollListingResponse getUserPayrollByDivision(UUID divisionId) {
    /* checks if division exists */
    Optional<Division> optDivision = divisionRepository.findOneActiveById(divisionId);
    if(!optDivision.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    /* */
    Sysconfig period = sysconfigExtRepository.findOneActiveCurrentPeriod().get();
    List<DivisionUserPayrollListingDTO> divisionUserPayrollListingDTOs = userPayrollRepository.findAllByPeriodIdAndDivisionId(period.getId(), divisionId)
      .stream()
      .map(mapperUtil::mapUserPayrollToDivisionUserPayrollListingDTO)
      .sorted(Comparator.comparing(DivisionUserPayrollListingDTO::getUserName))
      .collect(Collectors.toList());
    return new DivisionUserPayrollListingResponse(optDivision.get().getName(), divisionUserPayrollListingDTOs);
  }

  /**
   *
   */
  public void calculateByDivision(HttpServletResponse httpServletResponse, UUID periodId, UUID divisionId) throws IOException {
    /* check if division exists */
    Optional<Division> optDivision = divisionRepository.findOneActiveById(divisionId);
    if(!optDivision.isPresent()) {
      throw new IllegalArgumentException(DIVISION_NOT_FOUND);
    }
    /* check if period exists */
    Optional<Sysconfig> optPeriod = sysconfigRepository.findOneActiveById(periodId);
    if(!optPeriod.isPresent()) {
      throw new IllegalArgumentException(PERIOD_NOT_EXISTS);
    }
    /* helper fields */
    boolean bopsFlag = false;
    /* list of DTO to be written into excel */
    List<ExportUserPayrollDTO> exportUserPayrollDTOs = new ArrayList<>();
    /* get all UserAttendance object by period id and division id */
    List<UserAttendance> userAttendances = userAttendanceRepository.findAllActiveByPeriodIdAndDivisionId(periodId, optDivision.get().getId());
    /* get DivisionPayroll object for the given period and start calculation */
    DivisionPayroll divisionPayroll = optDivision.get().getDivisionPayrolls().stream().filter(dp -> dp.getPeriod().getId().toString().equals(periodId.toString())).findFirst().get();
    BigDecimal sumMKLSOutletNonKaresto = new BigDecimal(userAttendances.stream().filter(ua -> !ua.getDivisionUserAuth().getUserAuth().getUserInfo().getRole().getKey().equals(KARESTO)).mapToInt(UserAttendance::getCountMK).sum() + userAttendances.stream().filter(ua -> !ua.getDivisionUserAuth().getUserAuth().getUserInfo().getRole().getKey().equals(KARESTO)).mapToInt(UserAttendance::getCountLS).sum());
    for(UserAttendance userAttendance : userAttendances) {
      UserInfo userInfo = userAttendance.getDivisionUserAuth().getUserAuth().getUserInfo();
      /* tunjangan jabatan */
      String roleKey = userInfo.getRole().getKey();
      BigDecimal tunjanganJabatan = roleKey.equals(KARESTO) ? new BigDecimal("250000") : (roleKey.equals(WAKARESTO) || roleKey.equals(KASIR)) ? new BigDecimal("150000") : BigDecimal.ZERO;
      /* bops */
      BigDecimal bops = new BigDecimal("0");
      if((roleKey.equals(KARESTO) || roleKey.equals(WAKARESTO)) && !bopsFlag) {
        bops = new BigDecimal("200000");
        bopsFlag = true;
      }
      /* subsidi */
      BigDecimal subsidi = new BigDecimal(10000 * (userAttendance.getCountMK() + userAttendance.getCountLS() + userAttendance.getCountLN()));
      /* jumlah gaji pokok */
      /* NOTE: the number of active UserPayroll record for each user must be only ONE */
      UserPayroll userPayroll = userAttendance.getDivisionUserAuth().getUserAuth().getUserPayrolls().stream().filter(up -> !up.getDeletedFlag()).findFirst().get();
      BigDecimal jumlahGajiPokok = userPayroll.getGajiPokok().multiply(new BigDecimal(userAttendance.getCountMK() + userAttendance.getCountLS() + userAttendance.getCountLN() + userAttendance.getCountSKD() + userAttendance.getCountCT() + userAttendance.getCountDISP()));
      /* jumlah uang makan */
      BigDecimal jumlahUangMakan = userPayroll.getUangMakan().multiply(new BigDecimal(userAttendance.getCountMK() + userAttendance.getCountLS()));
      /* lembur LN */
      BigDecimal lemburLN = new BigDecimal(userAttendance.getCountLN()).multiply(userPayroll.getGajiPokok().add(userPayroll.getUangMakan()).add(tunjanganJabatan.divide(BIGD_25)));
      /* lembur LS */
      BigDecimal lemburLS = new BigDecimal(userAttendance.getCountLS()).multiply(userPayroll.getGajiPokok().add(userPayroll.getUangMakan()).add(tunjanganJabatan.divide(BIGD_25)));
      /* lembur OT */
      BigDecimal countOT = new BigDecimal(userAttendance.getCountOT());
      BigDecimal lemburOT = BIGD_1_PER_173.multiply(BIGD_25.multiply(userPayroll.getGajiPokok()).add(tunjanganJabatan).add(bops)).multiply(countOT).setScale(0, RoundingMode.HALF_UP);
      /* bonus harian & bonus kawaka */
      BigDecimal sumMKLSLNUser = new BigDecimal(userAttendance.getCountMK() + userAttendance.getCountLS() + userAttendance.getCountLN());
      BigDecimal bonusHarian = divisionPayroll.getJumlahBonusHarian().divide(sumMKLSOutletNonKaresto, 2, RoundingMode.HALF_UP).multiply(sumMKLSLNUser);
      BigDecimal bonusKawaka = roleKey.equals(KARESTO) ? divisionPayroll.getJumlahBonusKawaka() : roleKey.equals(WAKARESTO) ? BIGD_60_PERCENT.multiply(divisionPayroll.getJumlahBonusKawaka()) : new BigDecimal(0);
      if(bonusHarian.compareTo(bonusKawaka) < 0) {
        bonusHarian = new BigDecimal(0);
      } else {
        bonusKawaka = new BigDecimal(0);
      }
      /* TO-DO: THR */
      /* check if join date is equal or greater than 3 months ago from lebaran day */
      BigDecimal thr = new BigDecimal(0);
      /* lain-lain plus */
      BigDecimal lainLainPlus1 = new BigDecimal((userAttendance.getCountMK() + userAttendance.getCountLS() + userAttendance.getCountLN()) * 5000);
      BigDecimal lainLainPlus2 = new BigDecimal(150000);
      BigDecimal lainLainPlus = lainLainPlus1.compareTo(lainLainPlus2) < 0 ? lainLainPlus1 : lainLainPlus2;
      /* jumlah gaji bruto, jumlah potongan, gaji diterima */
      BigDecimal jumlahGajiBruto = tunjanganJabatan.add(bops).add(subsidi).add(jumlahGajiPokok).add(jumlahUangMakan)
        .add(lemburLN).add(lemburLS).add(lemburOT).add(bonusHarian).add(bonusKawaka).add(thr).add(lainLainPlus);
      BigDecimal casbon = BigDecimal.ZERO;
      BigDecimal punishment = BigDecimal.ZERO;
      BigDecimal bpjsk = BigDecimal.ZERO;
      BigDecimal bpjspt = BigDecimal.ZERO;
      BigDecimal pph21 = BigDecimal.ZERO;
      BigDecimal lainLainMinus = BigDecimal.ZERO;
      BigDecimal jumlahPotongan = casbon.add(punishment).add(bpjsk).add(bpjspt).add(pph21).add(lainLainMinus).add(subsidi);
      BigDecimal gajiDiterima = jumlahGajiBruto.subtract(jumlahPotongan);
      /* constructing export payroll DTO */
      ExportUserPayrollDTO exportUserPayrollDTO = new ExportUserPayrollDTO();
      exportUserPayrollDTO.setName(userInfo.getName());
      exportUserPayrollDTO.setRole(userInfo.getRole().getValue());
      exportUserPayrollDTO.setTunjanganJabatan(tunjanganJabatan);
      exportUserPayrollDTO.setBops(bops);
      exportUserPayrollDTO.setSubsidi(subsidi);
      exportUserPayrollDTO.setJumlahGajiPokok(jumlahGajiPokok);
      exportUserPayrollDTO.setJumlahUangMakan(jumlahUangMakan);
      exportUserPayrollDTO.setLemburLN(lemburLN);
      exportUserPayrollDTO.setLemburLS(lemburLS);
      exportUserPayrollDTO.setLemburOT(lemburOT);
      exportUserPayrollDTO.setBonusHarian(bonusHarian);
      exportUserPayrollDTO.setBonusKawaka(bonusKawaka);
      exportUserPayrollDTO.setThr(thr);
      exportUserPayrollDTO.setLainLainPlus(lainLainPlus);
      exportUserPayrollDTO.setCasbon(casbon);
      exportUserPayrollDTO.setPunishment(punishment);
      exportUserPayrollDTO.setBpjsk(bpjsk);
      exportUserPayrollDTO.setBpjspt(bpjspt);
      exportUserPayrollDTO.setPph21(pph21);
      exportUserPayrollDTO.setLainLainMinus(lainLainMinus);
      exportUserPayrollDTO.setJumlahGajiBruto(jumlahGajiBruto);
      exportUserPayrollDTO.setJumlahPotongan(jumlahPotongan);
      exportUserPayrollDTO.setGajiDiterima(gajiDiterima);
      exportUserPayrollDTO.setBsiAccount(userInfo.getBsiAccount());
      exportUserPayrollDTO.setMk(userAttendance.getCountMK());
      exportUserPayrollDTO.setLs(userAttendance.getCountLS());
      exportUserPayrollDTO.setLn(userAttendance.getCountLN());
      exportUserPayrollDTO.setSkd(userAttendance.getCountSKD());
      exportUserPayrollDTO.setCt(userAttendance.getCountCT());
      exportUserPayrollDTO.setDisp(userAttendance.getCountDISP());
      exportUserPayrollDTO.setOt(userAttendance.getCountOT());
      exportUserPayrollDTO.setGajiPokok(userPayroll.getGajiPokok());
      exportUserPayrollDTO.setUangMakan(userPayroll.getUangMakan());
      exportUserPayrollDTOs.add(exportUserPayrollDTO);
    }
    exportExcelService.exportPayrollToExcel(httpServletResponse, optDivision.get().getName(), optPeriod.get().getValue(), exportUserPayrollDTOs);
  }

}
