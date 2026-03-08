package com.fw.dbro.backend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fw.dbro.backend.dto.JwtClaimDTO;
import java.util.Date;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

  private final String JWT_SECRET = "dbro.backend.jwt.secret.123!@#";
  private final String JWT_SUBJECT = "userDetails";
  private final String CLAIM_KEY_USER_AUTH_ID = "userAuthId";
  private final String CLAIM_KEY_USER_EMAIL = "email";
  private final String CLAIM_KEY_USER_ROLE_ID = "roleId";
  private final String ISSUER = "Software House";

  public String generateToken(UUID userAuthId, String email, UUID roleId) {
    Date date = new Date();
    return JWT.create()
      .withSubject(JWT_SUBJECT)
      .withClaim(CLAIM_KEY_USER_AUTH_ID, userAuthId.toString())
      .withClaim(CLAIM_KEY_USER_EMAIL, email)
      .withClaim(CLAIM_KEY_USER_ROLE_ID, roleId.toString())
      .withIssuedAt(date)
      .withIssuer(ISSUER)
      .sign(Algorithm.HMAC256(JWT_SECRET));
  }

  public JwtClaimDTO validateTokenAndGetJwtClaimDTO(String jwt) throws JWTVerificationException {
    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRET))
      .withSubject(JWT_SUBJECT)
      .withIssuer(ISSUER)
      .build();
    DecodedJWT decodedJwt = jwtVerifier.verify(jwt);
    UUID userAuthId = UUID.fromString(decodedJwt.getClaim(CLAIM_KEY_USER_AUTH_ID).asString());
    String email = decodedJwt.getClaim(CLAIM_KEY_USER_EMAIL).asString();
    UUID roleId = UUID.fromString(decodedJwt.getClaim(CLAIM_KEY_USER_ROLE_ID).asString());
    return new JwtClaimDTO(userAuthId, email, roleId);
  }

}
