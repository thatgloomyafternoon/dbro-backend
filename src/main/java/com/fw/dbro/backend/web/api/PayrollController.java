package com.fw.dbro.backend.web.api;

import com.fw.dbro.backend.constants.ApiPaths;
import com.fw.dbro.backend.services.PayrollService;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API + ApiPaths.PAYROLL)
public class PayrollController {

  private PayrollService payrollService;

  public PayrollController(PayrollService payrollService) {
    this.payrollService = payrollService;
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_USER_BY_DIVISION)
  public ResponseEntity<?> listUserByDivision(@RequestParam(name = "divisionId", required = true) UUID divisionId) {
    return ResponseEntity.ok().body(payrollService.getUserPayrollByDivision(divisionId));
  }

  /**
   *
   */
  @GetMapping(ApiPaths.CALCULATE_BY_DIVISION)
  public void calculateByDivision(
    @RequestParam(name = "periodId", required = true) UUID periodId,
    @RequestParam(name = "divisionId", required = true) UUID divisionId,
    HttpServletResponse httpServletResponse
  ) throws IOException {
    payrollService.calculateByDivision(httpServletResponse, periodId, divisionId);
  }

}
