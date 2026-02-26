package com.fw.dbro.backend.entities;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

@Table(name = "division_payroll")
@Entity
public class DivisionPayroll extends BaseEntity {

  @JoinColumn(name = "period_id")
  @OneToOne
  @NotNull(message = PERIOD_NOT_NULL_MESSAGE)
  private Sysconfig period;

  @JoinColumn(name = "division_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = DIVISION_NOT_NULL_MESSAGE)
  private Division division;

  @Column(name = "jumlah_bonus_harian")
  @NotNull(message = JUMLAH_BONUS_HARIAN_NOT_NULL_MESSAGE)
  private BigDecimal jumlahBonusHarian;

  @Column(name = "jumlah_bonus_kawaka")
  @NotNull(message = JUMLAH_BONUS_KAWAKA_NOT_NULL_MESSAGE)
  private BigDecimal jumlahBonusKawaka;

  public Sysconfig getPeriod() {
    return period;
  }

  public void setPeriod(Sysconfig period) {
    this.period = period;
  }

  public Division getDivision() {
    return division;
  }

  public void setDivision(Division division) {
    this.division = division;
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
