# Change Log

# [0.5.0]
*2024-03-10*
### Updated
- TO-DO CHANGELOG.MD
### Added
- TO-DO CHANGELOG.MD
### Removed
- TO-DO CHANGELOG.MD

# [0.4.0]
*2024-02-23*
### Updated
- TO-DO CHANGELOG.MD
### Added
- TO-DO CHANGELOG.MD
### Removed
- TO-DO CHANGELOG.MD

# [0.3.0]
*2024-01-06*
### Updated
- ApiPaths
  - added /api/user/clock-in-by-group
  - added /api/division/list-clock-in-user-my-division
- MessageConstants
  - added some messages
- NumericConstants
  - updated SYSCONFIG_KEY_COLUMN_MAX_LENGTH from 30 to 100
- JwtClaimDTO
  - updated userId to userAuthId
- UserService
  - injected ClockInOutRepository
  - added implementation of clockInByGroup
- JwtUtil
  - updated userId to userAuthId
- MapperUtil
  - mapDivisionUserAuthToClockInUserDTO
- UserController
  - added api /api/user/clock-in-by-group
- application-develop.yaml
  - added AWS RDS DB URL
- application.yaml
  - added servlet.multipart.max-file-size: 5MB
  - added servlet.multipart.max-request-size: 5MB
### Added
- ClockInUserDTO
  - userAuthId
  - name
- ClockInOut entity
  - many-to-one userAuth
- Division entity
  - name
  - divisionUserAuths
- DivisionUserAuth entity
  - division
  - userAuth
- ClockInOutRepository (no custom query yet)
- DivisionRepository (no custom query yet)
- DivisionUserAuthRepository
  - findOneActiveDivisionByUserAuthId
- DivisionService
  - getListClockInUserMyDivision
- DivisionController
  - implemented api /api/division/list-clock-in-user-my-division
- ClockInUserListingResponse
  - clockInUserDTOs
- application-local.yaml
### Removed

# [0.2.0]
*2023-10-10*
### Updated
- added "Active" to all repositories method name and updated all references
- MessageConstants
  - USER_AUTH_NOT_NULL_MESSAGE
  - USER_INFO_NOT_NULL_MESSAGE
- PermissionService
  - refactored getAllPermissionsByRoleIdOptional
- SysconfigService
  - refactored getAllSysconfigsByType
- UserDetailsServiceExt
  - replaced UserRepository with new UserAuthRepository
- UserService
  - replaced User with UserAuth
  - replaced UserRepository with UserAuthRepository
- ListingResponse
  - updated totalCount from Integer to Long and updated all subclasses
- updated all test classes in regard of UserAuth & UserInfo
### Added
- UserAuth
- UserInfo
- UserAuthRepository
- UserInfoRepository
### Removed
- User
- UserRepository

# [0.1.0]
*2023-09-26*
### Updated
### Added
- build.gradle
  - added implementation spring-boot-starter-data-jpa
  - added implementation spring-boot-starter-validation
  - added implementation spring-boot-starter-security
  - added implementation spring-boot-starter-test
  - added implementation com.auth0:java-jwt
  - added implementation jakarta.xml.bind:jakarta.xml.bind-api
  - added implementation org.glassfish.jaxb:jaxb-runtime
  - added testImplementation com.h2database:h2
  - added testImplementation spring-security-test
  - added runtimeOnly org.postgresql:postgresql
- dependencies.gradle
  - spring_boot_starter_data_jpa_version 2.6.11
  - spring_boot_starter_validation_version 2.6.11
  - spring_boot_starter_security_version 2.6.11
  - spring_security_test_version 5.3.13.RELEASE
  - spring_boot_starter_test_version 2.6.11
  - auth0_jjwt_version 3.18.2
  - postgresql_version 42.3.6
  - h2database_version 1.4.200
  - jaxb_version 2.3.2
- JwtRequestFilter
  - using JWT for every protected resources
  - implemented filtering for the already-logged-out JWT
  - implemented permission checking
  - implemented authentication for the stateless policy
  - implemented JwtClaimDTO storage inside authentication details
- RestErrorControllerAdvice
  - handleMethodArgumentNotValidException (MethodArgumentNotValidException)
  - handleJwtVerificationException (JWTVerificationException)
  - handleJsonParseException (MismatchedInputException, JsonParseException)
  - handleInvalidUserException (InvalidUserException)
  - handleIllegalArgumentException (IllegalArgumentException)
  - handleMissingServletRequestParameterException (MissingServletRequestParameterException)
- WebSecurityConfig
  - not extending WebMvcConfigurerAdapter (using SecurityFilterChain bean)
  - PasswordEncoder bean (BCryptPasswordEncoder)
  - AuthenticationManager bean (using custom UserDetailsService: UserDetailsServiceExt)
  - SecurityFilterChain bean (CSRF disable, CORS enable, SessionCreationPolicy.STATELESS, userDetailsServiceExt, added JwtRequestFilter before UsernamePasswordAuthenticationFilter)
  - WebMvcConfigurer bean (added CORS mapping to all API paths from origin ["*", Tailscale IP, localhost:80]. This is to serve Flutter app on the dev server)
- ApiPaths
  - GET  /api/sysconfig-type/list
  - GET  /api/sysconfig-type/detail?name=
  - POST /api/sysconfig-type/create
  - GET  /api/sysconfig/list?type=&page=&size=
  - POST /api/sysconfig/create
  - GET  /api/permission/list-my-permission
  - GET  /api/permission/list?roleId=&page=&size=
  - POST /api/permission/create
  - POST /api/permission/delete
  - POST /api/user/login
  - POST /api/user/logout
- MessageConstants: validation messages (used in validation annotation), and other messages (used when throwing exceptions)
- NumericConstants
- SysconfigConstants
- JwtClaimDTO (userId, email, roleId)
- MyPermissionDTO (apiPathKey, apiPathValue)
- PermissionDTO (id, createdDate, createdBy, lastModifiedDate, lastModifiedBy, role, apiPathId, apiPathKey, apiPathValue)
- SysconfigDTO (id, createdDate, createdBy, lastModifiedDate, lastModifiedBy, key, value)
- SysconfigTypeDTO (id, createdDate, createdBy, lastModifiedDate, lastModifiedBy, name)
- BaseEntity (id, createdDate, createdBy, lastModifiedDate, lastModifiedBy, deletedFlag, deletedDate, deletedBy)
- InvalidJwt (jwt)
- Permission (role, apiPath)
- Sysconfig (sysconfigType, key, value)
- SysconfigType (name)
- User (email, password, role, name)
- InvalidUserException
- InvalidJwtRepository
  - findOneByJwt
- PermissionRepository
  - findAndPageActive
  - findAllByRoleIdAndApiPath
  - findAllByRoleId
  - findAndPageByRoleId
- SysconfigRepository
  - findAndPageActive
  - findAndPageByType
  - findOneByKey
- SysconfigTypeRepository
  - findAllActive
  - findOneByName
- UserRepository
  - findOneByEmail
- PermissionService
  - checkIfRoleAllowedForApiPath(UUID roleId, String apiPath)
  - getMyPermissions()
  - getAllPermissionsByRoleIdOptional(UUID roleId, int page, int size)
  - createPermission(UUID roleId, UUID apiPathId)
  - deletePermission(UUID permissionId)
- SysconfigService
  - getAllSysconfigsByType(String type, int page, int size)
  - createSysconfig(UUID sysconfigTypeId, String key, String value)
- SysconfigTypeService
  - getAllSysconfigTypes()
  - getSysconfigTypeDetail(String name)
  - createSysconfigType(String name)
- UserDetailsServiceExt
  - loadUserByUsername(String email)
- UserService
  - login(String email, String password)
  - logout(HttpServletRequest httpServletRequest)
- JwtUtil
  - generateToken(UUID userId, String email, UUID roleId)
  - validateTokenAndGetJwtClaimDTO(String jwt)
- MapperUtil
  - mapSysconfigToSysconfigDTO(Sysconfig sysconfig)
  - mapSysconfigTypeToSysconfigTypeDTO(SysconfigType sysconfigType)
  - mapPermissionToMyPermissionDTO(Permission permission)
  - mapPermissionToPermissionDTO(Permission permission)
- SpringBootBackEndStartup
  - run(Class<?> mainClass, String[] args)
  - logStartup(Environment env)
- PermissionController
  - listMyPermission()
  - list(@RequestParam(name = "roleId", required = false) UUID roleId, @RequestParam(name = "page", required = true) int page, @RequestParam(name = "size", required = true) int size)
  - create(@Validated @RequestBody PermissionCreateRequest request)
  - delete(@Validated @RequestBody PermissionDeleteRequest request)
- SysconfigController
  - list(@RequestParam(name = "type", required = false) String type, @RequestParam(name = "page", required = true) int page, @RequestParam(name = "size", required = true) int size)
  - create(@Validated @RequestBody SysconfigCreateRequest request)
- SysconfigTypeController
  - list()
  - detail(@RequestParam(name = "name", required = true) String name)
  - create(@Validated @RequestBody SysconfigTypeCreateRequest request)
- UserController
  - login(@Validated @RequestBody UserLoginRequest request)
  - logout(HttpServletRequest httpServletRequest)
- PermissionCreateRequest (roleId, apiPathId)
- PermissionDeleteRequest (permissionId)
- SysconfigCreateRequest (sysconfigTypeId, key, value)
- SysconfigTypeCreateRequest (name)
- UserLoginRequest (email, password)
- ListingResponse (totalCount)
- PermissionListingResponse (permissionDTOs)
- SimpleIdResponse (id)
- SimpleMessageResponse (message)
- SysconfigListingResponse (sysconfigDTOs)
- SysconfigTypeListingResponse (sysconfigTypeDTOs)
- UserLoginResponse (token)
- application-develop.yaml
  - spring.datasource.url
  - spring.datasource.username
  - spring.datasource.password
  - spring.jpa.hibernate.ddl-auto: create
  - spring.jpa.show-sql: true
- application.yaml
  - spring.application.name: dbro-backend
  - spring.profiles.active: develop
  - spring.jpa.open-in-view: false
  - spring.jpa.database-platform: org.hibernate.dialect.PostgreSQL10Dialect
- TestConstants (static fields and methods used in tests)
- TestApiPermissionCreate
  - givenAdministratorWithPermission_whenCreatingPermissionWithRoleIdNull_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingPermissionWithRoleIdNotReferringToActualRole_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingPermissionWithInvalidRoleId_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingPermissionWithApiPathIdNull_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingPermissionWithApiPathIdNotReferringToActualApiPath_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingPermissionWithInvalidApiPathId_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingAlreadyExistingPermission_assert400WithMessage
  - givenAdministratorWithPermission_whenCreatingPermission_assert200AndVerifyPermissionRepo
- TestApiPermissionDelete
  - givenAdministrator_whenDeletingPermission_withNoPermissionIdInPayload_assert400MethodArgumentNotValid
  - givenAdministrator_whenDeletingPermission_withPermissionIdNull_assert400MethodArgumentNotValid
  - givenAdministrator_whenDeletingPermission_withInvalidPermissionId_assert400PermissionNotFound
  - givenAdministrator_whenDeletingPermission_withValidPermissionId_assert200AndVerifyOutput
- TestApiPermissionList
  - givenAdministrator_whenListingPermissions_withoutPassingRoleId_assert200AndVerifyAllPermissions
  - givenAdministrator_whenListingPermissionsFirstPageWithRoleId_assert200AndVerifyListing
  - givenAdministrator_whenListingPermissionsSecondPageWithRoleId_assert200AndVerifyListing
  - givenAdministrator_whenListingPermissionsWithUnknownRoleId_assert200AndVerifyListing
  - givenAdministrator_whenListingPermissionsWithoutRequiredRequestParam_assert400
- TestApiPermissionListMyPermission
  - givenAdministrator_whenListingMyPermission_withoutPermission_assert403
  - givenAdministrator_whenListingMyPermission_withInjectedPermission_assert200AndCorrectResponse
  - givenAdministrator_whenListingMyPermission_withInjectedAndUpdatedPermission_assert200AndCorrectResponse
- TestApiSysconfigCreate
  - givenSystemMaster_whenCreatingSysconfigForAdministratorRole_assert200AndCorrectResponse
  - givenAdministrator_whenCreatingSysconfigDummy_withInjectedPermission_assert200AndCorrectResponse
  - givenAdministrator_whenCreatingSysconfigWithUnknownSysconfigTypeId_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithNullKey_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithBlankKey_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithKeyExceedingLength_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithExistingKey_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithNullValue_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithBlankValue_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigWithValueExceedingLength_withInjectedPermission_assert400WithMessage
- TestApiSysconfigList
  - givenSystemMaster_whenListingSysconfigAll_assert200AndCorrectListing
  - givenSystemMaster_whenListingSysconfigRole_assert200AndCorrectListin
  - givenSystemMaster_whenListingSysconfigApiPath_assert200AndCorrectListing
  - givenAdministrator_whenListingSysconfigAll_withoutPermission_assert403
  - givenAdministrator_whenListingSysconfigAll_withInjectedPermission_assert200AndCorrectListing
  - givenAdministrator_whenListingSysconfigWithNonExistingType_withInjectedPermission_assert200AndCorrectListing
  - givenAdministrator_whenListingSysconfigWithoutGivingRequiredRequestParam_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenListingSysconfigApiPath_withInjectedPermission_assert200AndCorrectPagination
- TestApiSysconfigTypeCreate
  - givenSystemMaster_whenCreatingSysconfigType_assert200AndCorrectResponse
  - givenAdministrator_whenCreatingSysconfigType_withoutPermission_assert403
  - givenAdministrator_whenCreatingSysconfigTypeGender_withInjectedPermission_assert200AndCorrectResponse
  - givenAdministrator_whenCreatingSysconfigTypeNameBlank_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigTypeNameNull_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigTypeNameExceedsLength_withInjectedPermission_assert400WithMessage
  - givenAdministrator_whenCreatingSysconfigTypeNameAlreadyExists_withInjectedPermission_assert400WithMessage
- TestApiSysconfigTypeDetail
  - givenAdministrator_whenGettingSysconfigTypeDetail_withoutGivingNameRequestParam_assert400WithMessage
  - givenAdministrator_whenGettingSysconfigTypeDetail_withNonExistingNameRequestParam_assert400WithMessage
  - givenAdministrator_whenGettingSysconfigTypeDetail_withCorrectName_assert200AndVerifyResponse
- TestApiSysconfigTypeList
  - givenSystemMaster_whenListingSysconfigType_assert200AndCorrectListing
  - givenAdministrator_whenListingSysconfigType_withoutPermission_assert403
  - givenAdministrator_whenListingSysconfigType_withInjectedPermission_assert200AndCorrectListing
- TestApiUserLogin
  - givenNoAccount_whenLoggingIn_withInvalidPayload_assert400AndMessage
  - givenNoAccount_whenLoggingIn_withValidCreds_assert403
  - givenSystemMasterAccount_whenLoggingIn_withNullEmail_assert400
  - givenSystemMasterAccount_whenLoggingIn_withNullPassword_assert400
  - givenSystemMasterAccount_whenLoggingIn_withUnregisteredEmail_assert403
  - givenSystemMasterAccount_whenLoggingIn_withInvalidPassword_assert403
  - givenSystemMasterAccount_whenLoggingIn_withValidCreds_assert200AndGetToken
- TestApiUserLogout
  - givenLoggedInSystemMaster_whenLoggingOut_assert200WithMessage
  - givenLoggedInSystemMaster_whenLoggingOut2x_assert403
  - givenLoggedInAdministrator_whenLoggingOut_withoutPermission_assert403
  - givenLoggedInAdministrator_whenLoggingOut_withInjectedPermission_assert200
### Removed
<br><br>

# [0.0.1]
*2023-08-13*
### Added / Updated
- .gitignore
- minimum runnable spring-boot back end app with no APIs
  - spring boot 2.6.11
  - Java 17
  - spring boot starter web 2.6.11
### Removed
<br><br>
