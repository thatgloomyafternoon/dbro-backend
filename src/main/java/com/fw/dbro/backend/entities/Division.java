package com.fw.dbro.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import static com.fw.dbro.backend.constants.MessageConstants.*;
import static com.fw.dbro.backend.constants.NumericConstants.*;

@Table(name = "division")
@Entity
public class Division extends BaseEntity {

  @Column(name = "root_flag")
  @NotNull(message = ROOT_FLAG_NOT_NULL_MESSAGE)
  private Boolean rootFlag;

  @Column(name = "name", length = NAME_COLUMN_MAX_LENGTH)
  @Size(min = 3, max = NAME_COLUMN_MAX_LENGTH, message = NAME_TOO_SHORT_OR_TOO_LONG_MESSAGE)
  @NotNull(message = NAME_NOT_NULL_MESSAGE)
  @NotBlank(message = NAME_NOT_BLANK_MESSAGE)
  private String name;

  @OneToMany(mappedBy = "division")
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonIgnore
  private List<DivisionUserAuth> divisionUserAuths;

  @OneToMany(mappedBy = "childDivision")
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonIgnore
  private List<DivisionStructure> childDivisions;

  @OneToMany(mappedBy = "division", fetch = FetchType.EAGER)
  private List<DivisionPayroll> divisionPayrolls;

  public Boolean getRootFlag() {
    return rootFlag;
  }

  public void setRootFlag(Boolean rootFlag) {
    this.rootFlag = rootFlag;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<DivisionUserAuth> getDivisionUserAuths() {
    return divisionUserAuths;
  }

  public void setDivisionUserAuths(List<DivisionUserAuth> divisionUserAuths) {
    this.divisionUserAuths = divisionUserAuths;
  }

  public List<DivisionStructure> getChildDivisions() {
    return childDivisions;
  }

  public void setChildDivisions(List<DivisionStructure> childDivisions) {
    this.childDivisions = childDivisions;
  }

  public List<DivisionPayroll> getDivisionPayrolls() {
    return divisionPayrolls;
  }

  public void setDivisionPayrolls(List<DivisionPayroll> divisionPayrolls) {
    this.divisionPayrolls = divisionPayrolls;
  }

}
