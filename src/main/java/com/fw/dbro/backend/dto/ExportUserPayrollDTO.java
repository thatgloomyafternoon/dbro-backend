package com.fw.dbro.backend.dto;

import java.math.BigDecimal;

public class ExportUserPayrollDTO {

  /* plus modifier */
  private String name;
  private String role;
  private BigDecimal tunjanganJabatan;
  private BigDecimal bops;
  private BigDecimal subsidi;
  private BigDecimal jumlahGajiPokok;
  private BigDecimal jumlahUangMakan;
  private BigDecimal lemburLN;
  private BigDecimal lemburLS;
  private BigDecimal lemburOT;
  private BigDecimal bonusHarian;
  private BigDecimal bonusKawaka;
  private BigDecimal thr;
  private BigDecimal lainLainPlus;
  /* minus modifier */
  private BigDecimal casbon;
  private BigDecimal punishment;
  private BigDecimal bpjsk;
  private BigDecimal bpjspt;
  private BigDecimal pph21;
  private BigDecimal lainLainMinus;
  /* total */
  private BigDecimal jumlahGajiBruto;
  private BigDecimal jumlahPotongan;
  private BigDecimal gajiDiterima;
  private String bsiAccount;
  /* base */
  private Integer mk;
  private Integer ls;
  private Integer ln;
  private Integer skd;
  private Integer ct;
  private Integer disp;
  private Double ot;
  private BigDecimal gajiPokok;
  private BigDecimal uangMakan;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public BigDecimal getTunjanganJabatan() {
    return tunjanganJabatan;
  }

  public void setTunjanganJabatan(BigDecimal tunjanganJabatan) {
    this.tunjanganJabatan = tunjanganJabatan;
  }

  public BigDecimal getBops() {
    return bops;
  }

  public void setBops(BigDecimal bops) {
    this.bops = bops;
  }

  public BigDecimal getSubsidi() {
    return subsidi;
  }

  public void setSubsidi(BigDecimal subsidi) {
    this.subsidi = subsidi;
  }

  public BigDecimal getJumlahGajiPokok() {
    return jumlahGajiPokok;
  }

  public void setJumlahGajiPokok(BigDecimal jumlahGajiPokok) {
    this.jumlahGajiPokok = jumlahGajiPokok;
  }

  public BigDecimal getJumlahUangMakan() {
    return jumlahUangMakan;
  }

  public void setJumlahUangMakan(BigDecimal jumlahUangMakan) {
    this.jumlahUangMakan = jumlahUangMakan;
  }

  public BigDecimal getLemburLN() {
    return lemburLN;
  }

  public void setLemburLN(BigDecimal lemburLN) {
    this.lemburLN = lemburLN;
  }

  public BigDecimal getLemburLS() {
    return lemburLS;
  }

  public void setLemburLS(BigDecimal lemburLS) {
    this.lemburLS = lemburLS;
  }

  public BigDecimal getLemburOT() {
    return lemburOT;
  }

  public void setLemburOT(BigDecimal lemburOT) {
    this.lemburOT = lemburOT;
  }

  public BigDecimal getBonusHarian() {
    return bonusHarian;
  }

  public void setBonusHarian(BigDecimal bonusHarian) {
    this.bonusHarian = bonusHarian;
  }

  public BigDecimal getBonusKawaka() {
    return bonusKawaka;
  }

  public void setBonusKawaka(BigDecimal bonusKawaka) {
    this.bonusKawaka = bonusKawaka;
  }

  public BigDecimal getThr() {
    return thr;
  }

  public void setThr(BigDecimal thr) {
    this.thr = thr;
  }

  public BigDecimal getLainLainPlus() {
    return lainLainPlus;
  }

  public void setLainLainPlus(BigDecimal lainLainPlus) {
    this.lainLainPlus = lainLainPlus;
  }

  public BigDecimal getCasbon() {
    return casbon;
  }

  public void setCasbon(BigDecimal casbon) {
    this.casbon = casbon;
  }

  public BigDecimal getPunishment() {
    return punishment;
  }

  public void setPunishment(BigDecimal punishment) {
    this.punishment = punishment;
  }

  public BigDecimal getBpjsk() {
    return bpjsk;
  }

  public void setBpjsk(BigDecimal bpjsk) {
    this.bpjsk = bpjsk;
  }

  public BigDecimal getBpjspt() {
    return bpjspt;
  }

  public void setBpjspt(BigDecimal bpjspt) {
    this.bpjspt = bpjspt;
  }

  public BigDecimal getPph21() {
    return pph21;
  }

  public void setPph21(BigDecimal pph21) {
    this.pph21 = pph21;
  }

  public BigDecimal getLainLainMinus() {
    return lainLainMinus;
  }

  public void setLainLainMinus(BigDecimal lainLainMinus) {
    this.lainLainMinus = lainLainMinus;
  }

  public BigDecimal getJumlahGajiBruto() {
    return jumlahGajiBruto;
  }

  public void setJumlahGajiBruto(BigDecimal jumlahGajiBruto) {
    this.jumlahGajiBruto = jumlahGajiBruto;
  }

  public BigDecimal getJumlahPotongan() {
    return jumlahPotongan;
  }

  public void setJumlahPotongan(BigDecimal jumlahPotongan) {
    this.jumlahPotongan = jumlahPotongan;
  }

  public BigDecimal getGajiDiterima() {
    return gajiDiterima;
  }

  public void setGajiDiterima(BigDecimal gajiDiterima) {
    this.gajiDiterima = gajiDiterima;
  }

  public String getBsiAccount() {
    return bsiAccount;
  }

  public void setBsiAccount(String bsiAccount) {
    this.bsiAccount = bsiAccount;
  }

  public Integer getMk() {
    return mk;
  }

  public void setMk(Integer mk) {
    this.mk = mk;
  }

  public Integer getLs() {
    return ls;
  }

  public void setLs(Integer ls) {
    this.ls = ls;
  }

  public Integer getLn() {
    return ln;
  }

  public void setLn(Integer ln) {
    this.ln = ln;
  }

  public Integer getSkd() {
    return skd;
  }

  public void setSkd(Integer skd) {
    this.skd = skd;
  }

  public Integer getCt() {
    return ct;
  }

  public void setCt(Integer ct) {
    this.ct = ct;
  }

  public Integer getDisp() {
    return disp;
  }

  public void setDisp(Integer disp) {
    this.disp = disp;
  }

  public Double getOt() {
    return ot;
  }

  public void setOt(Double ot) {
    this.ot = ot;
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

}
