package com.fw.dbro.backend.web.requests;

import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

public class DivisionUpdatePayrollInfoRequest {

  @NotNull(message = DIVISION_ID_NOT_NULL_MESSAGE)
  private UUID divisionId;

  @NotNull(message = JUMLAH_BONUS_HARIAN_NOT_NULL_MESSAGE)
  @DecimalMin(value = "0.0", inclusive = true, message = JUMLAH_BONUS_HARIAN_NOT_NEGATIVE_MESSAGE)
  private BigDecimal jumlahBonusHarian;

  @NotNull(message = JUMLAH_BONUS_KAWAKA_NOT_NULL_MESSAGE)
  @DecimalMin(value = "0.0", inclusive = true, message = JUMLAH_BONUS_KAWAKA_NOT_NEGATIVE_MESSAGE)
  private BigDecimal jumlahBonusKawaka;

  public UUID getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(UUID divisionId) {
    this.divisionId = divisionId;
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
