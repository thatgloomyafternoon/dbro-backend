package com.fw.dbro.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

@Table(name = "invalid_jwt")
@Entity
public class InvalidJwt extends BaseEntity {

  @Column(name = "jwt", length = JWT_COLUMN_MAX_LENGTH)
  @Size(min = 1, max = JWT_COLUMN_MAX_LENGTH, message = JWT_MUST_NOT_EXCEEDS_MAX_MESSAGE + JWT_COLUMN_MAX_LENGTH)
  @NotNull
  @NotBlank
  private String jwt;

  public String getJwt() {
    return jwt;
  }

  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

}
