package com.fw.dbro.backend.web.api;

import com.fw.dbro.backend.constants.ApiPaths;
import com.fw.dbro.backend.services.DivisionService;
import com.fw.dbro.backend.web.requests.DivisionAddUserRequest;
import com.fw.dbro.backend.web.requests.DivisionCreateRequest;
import com.fw.dbro.backend.web.requests.DivisionRemoveUserRequest;
import com.fw.dbro.backend.web.requests.DivisionStructureCreateRequest;
import com.fw.dbro.backend.web.requests.DivisionUpdatePayrollInfoRequest;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiPaths.API + ApiPaths.DIVISION)
public class DivisionController {

  private DivisionService divisionService;

  public DivisionController(DivisionService divisionService) {
    this.divisionService = divisionService;
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_CLOCK_IN_USER_MY_DIVISION)
  public ResponseEntity<?> listClockInUserMyDivision() {
    return ResponseEntity.ok().body(divisionService.getListClockInUserMyDivision());
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_ROOT)
  public ResponseEntity<?> listRootDivision() {
    return ResponseEntity.ok().body(divisionService.getListRootDivision());
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_CHILD)
  public ResponseEntity<?> listChildFromOneDivision(@RequestParam(name = "divisionId", required = true) UUID divisionId) {
    return ResponseEntity.ok().body(divisionService.getListChildFromOneDivision(divisionId));
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_USER)
  public ResponseEntity<?> listUsersFromOneDivision(@RequestParam(name = "divisionId", required = true) UUID divisionId) {
    return ResponseEntity.ok().body(divisionService.getAllUsersFromOneDivision(divisionId));
  }

  /**
   *
   */
  @GetMapping(ApiPaths.LIST_OUTLET)
  public ResponseEntity<?> listOutlet() {
    return ResponseEntity.ok().body(divisionService.getListOutlet());
  }

  /**
   *
   */
  @PostMapping(ApiPaths.CREATE)
  public ResponseEntity<?> createDivision(@Validated @RequestBody DivisionCreateRequest request) {
    return ResponseEntity.ok().body(divisionService.createDivision(request.getName(), request.getRootFlag(), request.getOutletFlag()));
  }

  /**
   *
   */
  @GetMapping(ApiPaths.DROPDOWN)
  public ResponseEntity<?> dropdown() {
    return ResponseEntity.ok().body(divisionService.getDivisionDropdown());
  }

  /**
   *
   */
  @PostMapping(ApiPaths.CREATE_RELATION)
  public ResponseEntity<?> createRelation(@RequestBody @Validated DivisionStructureCreateRequest request) {
    return ResponseEntity.ok().body(divisionService.createDivisionRelation(request.getParentDivisionId(), request.getChildDivisionId()));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.ADD_USER)
  public ResponseEntity<?> addUser(@RequestBody @Validated DivisionAddUserRequest request) {
    return ResponseEntity.ok().body(divisionService.addUserToDivision(request.getDivisionId(), request.getUserAuthId()));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.REMOVE_USER)
  public ResponseEntity<?> removeUser(@RequestBody @Validated DivisionRemoveUserRequest request) {
    return ResponseEntity.ok().body(divisionService.removeUserFromDivision(request.getDivisionId(), request.getUserAuthId()));
  }

  /**
   *
   */
  @PostMapping(ApiPaths.UPDATE_PAYROLL_INFO)
  public ResponseEntity<?> updatePayrollInfo(@RequestBody @Validated DivisionUpdatePayrollInfoRequest request) {
    return ResponseEntity.ok().body(divisionService.updatePayrollInfo(request.getDivisionId(), request.getJumlahBonusHarian(), request.getJumlahBonusKawaka()));
  }

}
