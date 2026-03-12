package com.fw.dbro.backend.web.api;

import com.fw.dbro.backend.constants.ApiPaths;
import com.fw.dbro.backend.services.SysconfigService;
import com.fw.dbro.backend.web.requests.SysconfigCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API + ApiPaths.SYSCONFIG)
public class SysconfigController {

  private SysconfigService sysconfigService;

  public SysconfigController(SysconfigService sysconfigService) {
    this.sysconfigService = sysconfigService;
  }

  /**
   * NOTE:
   * - 'type' refers to 'SysconfigType.name'
   */
  @GetMapping(ApiPaths.LIST)
  public ResponseEntity<?> list(
    @RequestParam(name = "type", required = false) String type,
    @RequestParam(name = "page", required = true) int page,
    @RequestParam(name = "size", required = true) int size
  ) {
    return ResponseEntity.ok().body(sysconfigService.getAllSysconfigsByType(page, size, type));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.CREATE)
  public ResponseEntity<?> create(@Validated @RequestBody SysconfigCreateRequest request) {
    return ResponseEntity.ok().body(sysconfigService.createSysconfig(request.getSysconfigTypeId(), request.getKey(), request.getValue()));
  }

  /**
   *
   */
  @GetMapping(ApiPaths.ROLE_DROPDOWN)
  public ResponseEntity<?> roleDropdown() {
    return ResponseEntity.ok().body(sysconfigService.getRoleDropdown());
  }

  /**
   *
   */
  @GetMapping(ApiPaths.PERIOD_DROPDOWN)
  public ResponseEntity<?> periodDropdown() {
    return ResponseEntity.ok().body(sysconfigService.getPeriodDropdown());
  }

}
