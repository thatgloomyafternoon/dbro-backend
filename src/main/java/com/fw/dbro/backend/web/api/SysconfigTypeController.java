package com.fw.dbro.backend.web.api;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fw.dbro.backend.constants.ApiPaths;
import com.fw.dbro.backend.services.SysconfigTypeService;
import com.fw.dbro.backend.web.requests.SysconfigTypeCreateRequest;

@RestController
@RequestMapping(ApiPaths.API + ApiPaths.SYSCONFIG_TYPE)
public class SysconfigTypeController {

  private SysconfigTypeService sysconfigTypeService;

  public SysconfigTypeController(SysconfigTypeService sysconfigTypeService) {
    this.sysconfigTypeService = sysconfigTypeService;
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST)
  public ResponseEntity<?> list() {
    return ResponseEntity.ok().body(sysconfigTypeService.getAllSysconfigTypes());
  }

  @GetMapping(ApiPaths.DETAIL)
  public ResponseEntity<?> detail(@RequestParam(name = "name", required = true) String name) {
    return ResponseEntity.ok().body(sysconfigTypeService.getSysconfigTypeDetail(name));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.CREATE)
  public ResponseEntity<?> create(@Validated @RequestBody SysconfigTypeCreateRequest request) {
    return ResponseEntity.ok().body(sysconfigTypeService.createSysconfigType(request.getName()));
  }

}
