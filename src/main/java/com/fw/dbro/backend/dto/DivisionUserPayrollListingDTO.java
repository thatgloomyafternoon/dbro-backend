package com.fw.dbro.backend.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class DivisionUserPayrollListingDTO {

  private UUID userAuthId;
  private String userName;
  private String joinDate;
  private BigDecimal gajiPokok;
  private BigDecimal uangMakan;
  private BigDecimal potonganLainLain;

  public UUID getUserAuthId() {
    return userAuthId;
  }

  public void setUserAuthId(UUID userAuthId) {
    this.userAuthId = userAuthId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
  }

  public BigDecimal getGajiPokok() {
    return gajiPokok;
  }

  public void setGajiPokok(BigDecimal gajiPokok) {
    this.gajiPokok = gajiPokok;
  }

  public BigDecimal getUangMakan() {
    return uangMakan;
  }

  public void setUangMakan(BigDecimal uangMakan) {
    this.uangMakan = uangMakan;
  }

  public BigDecimal getPotonganLainLain() {
    return potonganLainLain;
  }

  public void setPotonganLainLain(BigDecimal potonganLainLain) {
    this.potonganLainLain = potonganLainLain;
  }

}
