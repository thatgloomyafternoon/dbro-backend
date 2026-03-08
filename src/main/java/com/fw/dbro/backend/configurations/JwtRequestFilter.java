package com.fw.dbro.backend.configurations;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fw.dbro.backend.dto.JwtClaimDTO;
import com.fw.dbro.backend.entities.InvalidJwt;
import com.fw.dbro.backend.entities.Sysconfig;
import com.fw.dbro.backend.repositories.InvalidJwtRepository;
import com.fw.dbro.backend.repositories.SysconfigRepository;
import com.fw.dbro.backend.services.PermissionService;
import com.fw.dbro.backend.services.UserDetailsServiceExt;
import com.fw.dbro.backend.utils.JwtUtil;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.SystemConstants.*;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  private JwtUtil jwtUtil;
  private SysconfigRepository sysconfigRepository;
  private InvalidJwtRepository invalidJwtRepository;
  private UserDetailsServiceExt userDetailsServiceExt;
  private PermissionService permissionService;

  public JwtRequestFilter(
    JwtUtil jwtUtil,
    SysconfigRepository sysconfigRepository,
    InvalidJwtRepository invalidJwtRepository,
    UserDetailsServiceExt userDetailsServiceExt,
    PermissionService permissionService
  ) {
    this.jwtUtil = jwtUtil;
    this.sysconfigRepository = sysconfigRepository;
    this.invalidJwtRepository = invalidJwtRepository;
    this.userDetailsServiceExt = userDetailsServiceExt;
    this.permissionService = permissionService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
  throws ServletException, IOException, JWTVerificationException {
    String authHeader = request.getHeader("Authorization");
    if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
      String jwt = authHeader.substring(7);
      if(jwt == null || jwt.isBlank()) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, INVALID_TOKEN);
      } else {
        JwtClaimDTO jwtClaimDTO = jwtUtil.validateTokenAndGetJwtClaimDTO(jwt);
        /* check if the JWT used is invalid (already logged out) */
        Optional<InvalidJwt> optInvalidJwt = invalidJwtRepository.findOneActiveByJwt(jwt);
        if(optInvalidJwt.isPresent()) {
          response.sendError(HttpServletResponse.SC_FORBIDDEN, INVALID_TOKEN);
          return;
        }
        /* check permission of the requesting role */
        Optional<Sysconfig> optSysconfig = sysconfigRepository.findById(jwtClaimDTO.getRoleId());
        if(!optSysconfig.isPresent()) {
          response.sendError(HttpServletResponse.SC_BAD_REQUEST, INVALID_PERMISSION);
        } else if(!optSysconfig.get().getKey().equals(SYSTEM_MASTER) && !permissionService.checkIfRoleAllowedForApiPath(jwtClaimDTO.getRoleId(), request.getRequestURI())) {
          response.sendError(HttpServletResponse.SC_FORBIDDEN, INVALID_PERMISSION);
          return;
        }
        /* set authentication for the stateless policy */
        if(SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails userDetails = userDetailsServiceExt.loadUserByUsername(jwtClaimDTO.getEmail());
          UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            userDetails.getUsername(),
            userDetails.getPassword(),
            Collections.singletonList(new SimpleGrantedAuthority(jwtClaimDTO.getEmail()))
          );
          SecurityContextHolder.getContext().setAuthentication(auth);
          ((UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).setDetails(jwtClaimDTO);
        }
      }
    }
    filterChain.doFilter(request, response);
  }

}
