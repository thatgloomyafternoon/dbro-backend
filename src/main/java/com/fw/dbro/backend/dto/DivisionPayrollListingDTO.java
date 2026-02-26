package com.fw.dbro.backend.dto;

import java.math.BigDecimal;

public class DivisionPayrollListingDTO extends DivisionListingDTO {

  private String lastModifiedDate;
  private String lastModifiedBy;
  private BigDecimal jumlahBonusHarian;
  private BigDecimal jumlahBonusKawaka;

  public String getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(String lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public BigDecimal getJumlahBonusHarian() {
    return jumlahBonusHarian;
  }

  public void setJumlahBonusHarian(BigDecimal jumlahBonusHarian) {
    this.jumlahBonusHarian = jumlahBonusHarian;
  }

  public BigDecimal getJumlahBonusKawaka() {
    return jumlahBonusKawaka;
  }

  public void setJumlahBonusKawaka(BigDecimal jumlahBonusKawaka) {
    this.jumlahBonusKawaka = jumlahBonusKawaka;
  }

}
