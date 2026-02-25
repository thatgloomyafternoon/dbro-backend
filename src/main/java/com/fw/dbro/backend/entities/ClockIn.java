package com.fw.dbro.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

@Table(name = "clock_in")
@Entity
public class ClockIn extends BaseEntity {

  @JoinColumn(name = "division_user_auth_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = DIVISION_USER_AUTH_NOT_NULL_MESSAGE)
  private DivisionUserAuth divisionUserAuth;

  @JoinColumn(name = "clock_type_id")
  @OneToOne
  @NotNull(message = CLOCK_TYPE_NOT_NULL_MESSAGE)
  private Sysconfig clockType;

  @Column(name = "late_flag")
  @NotNull
  private Boolean lateFlag;

  public DivisionUserAuth getDivisionUserAuth() {
    return divisionUserAuth;
  }

  public void setDivisionUserAuth(DivisionUserAuth divisionUserAuth) {
    this.divisionUserAuth = divisionUserAuth;
  }

  public Sysconfig getClockType() {
    return clockType;
  }

  public void setClockType(Sysconfig clockType) {
    this.clockType = clockType;
  }

  public Boolean getLateFlag() {
    return lateFlag;
  }

  public void setLateFlag(Boolean lateFlag) {
    this.lateFlag = lateFlag;
  }

}
