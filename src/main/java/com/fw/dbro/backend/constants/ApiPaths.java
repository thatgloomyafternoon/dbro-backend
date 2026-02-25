package com.fw.dbro.backend.constants;

public interface ApiPaths {

  /* ALL */
  /**
   * NOTES:
   * []  --> not implemented
   * [.] --> implemented
   * [:] --> implemented with integration tests
   * [v] --> integrated with front-end
   * ------------------------------------------------------------
   * [v] GET  /api/sysconfig-type/list
   * [v] GET  /api/sysconfig-type/detail?name=
   * [:] POST /api/sysconfig-type/create
   * [v] GET  /api/sysconfig/list?type=&page=&size=
   * [v] POST /api/sysconfig/create (only role)
   * [:] GET  /api/sysconfig/role-dropdown
   * [:] GET  /api/sysconfig/period-dropdown
   * [v] GET  /api/permission/list-my-permission
   * [v] GET  /api/permission/list?roleId=&page=&size=
   * [v] POST /api/permission/create
   * [v] POST /api/permission/delete
   * [v] POST /api/user/login
   * [v] POST /api/user/logout
   * [:] GET  /api/user/dropdown (retrieve all except users with role CLOCK_IN_USER)
   * [v] POST /api/user/clock-in-by-group
   * [:] POST /api/user/clock-out-by-group
   * [.] POST /api/user/overtime-in-by-group
   * [.] POST /api/user/overtime-out-by-group
   * [:] POST /api/user/register
   * [:] POST /api/user/verify-register
   * [:] POST /api/user/update-payroll-info
   * [:] GET  /api/user/list-all?page=&size=
   * [:] GET  /api/user/list-attendance
   * [v] GET  /api/division/list-clock-in-user-my-division
   * [:] GET  /api/division/list-root
   * [:] GET  /api/division/list-child?divisionId=
   * [:] GET  /api/division/list-user?divisionId=
   * [:] GET  /api/division/list-outlet
   * [:] POST /api/division/create
   * [:] GET  /api/division/dropdown (retrieve all except root division and already-a-child division)
   * [:] POST /api/division/create-relation
   * [:] POST /api/division/add-user
   * [:] POST /api/division/remove-user
   * [:] POST /api/division/update-payroll-info
   * [:] GET  /api/payroll/calculate-by-division?periodId=&divisionId= (manual test)
   * [:] GET  /api/payroll/list-user-by-division?divisionId=
   * [:] GET  /api/clock-in/list-by-user?userAuthId=
   * [:] GET  /api/clock-in/filter-by-user-date?userAuthId=&startDate=&endDate=
   */

  /* base */
  String API = "/api";

  /* level-1 */
  String SYSCONFIG_TYPE = "/sysconfig-type";
  String SYSCONFIG = "/sysconfig";
  String PERMISSION = "/permission";
  String USER = "/user";
  String DIVISION = "/division";
  String PAYROLL = "/payroll";
  String CLOCK_IN = "/clock-in";

  /* level-2 */
  String LIST = "/list";
  String LIST_ROOT = "/list-root";
  String LIST_CHILD = "/list-child";
  String LIST_USER = "/list-user";
  String LIST_ALL = "/list-all";
  String LIST_OUTLET = "/list-outlet";
  String DETAIL = "/detail";
  String LIST_MY_PERMISSION = "/list-my-permission";
  String CREATE = "/create";
  String LOGIN = "/login";
  String LOGOUT = "/logout";
  String DELETE = "/delete";
  String LIST_CLOCK_IN_USER_MY_DIVISION = "/list-clock-in-user-my-division";
  String CLOCK_IN_BY_GROUP = "/clock-in-by-group";
  String CLOCK_OUT_BY_GROUP = "/clock-out-by-group";
  String OVERTIME_IN_BY_GROUP = "/overtime-in-by-group";
  String OVERTIME_OUT_BY_GROUP = "/overtime-out-by-group";
  String DROPDOWN = "/dropdown";
  String ROLE_DROPDOWN = "/role-dropdown";
  String PERIOD_DROPDOWN = "/period-dropdown";
  String CREATE_RELATION = "/create-relation";
  String ADD_USER = "/add-user";
  String REMOVE_USER = "/remove-user";
  String REGISTER = "/register";
  String VERIFY_REGISTER = "/verify-register";
  String UPDATE_PAYROLL_INFO = "/update-payroll-info";
  String CALCULATE_BY_DIVISION = "/calculate-by-division";
  String LIST_BY_USER = "/list-by-user";
  String LIST_USER_BY_DIVISION = "/list-user-by-division";
  String LIST_ATTENDANCE = "/list-attendance";
  String FILTER_BY_USER_DATE = "/filter-by-user-date";

}
