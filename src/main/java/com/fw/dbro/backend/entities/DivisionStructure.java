package com.fw.dbro.backend.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

@Table(name = "division_structure")
@Entity
public class DivisionStructure extends BaseEntity {

  @JoinColumn(name = "parent_division_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = DIVISION_NOT_NULL_MESSAGE)
  private Division parentDivision;

  /**
   * This is created one-to-one because a division can only have
   * one parent division at a time.
   */
  @JoinColumn(name = "child_division_id", referencedColumnName = "id")
  @OneToOne
  @NotNull(message = DIVISION_NOT_NULL_MESSAGE)
  private Division childDivision;

  public Division getParentDivision() {
    return parentDivision;
  }

  public void setParentDivision(Division parentDivision) {
    this.parentDivision = parentDivision;
  }

  public Division getChildDivision() {
    return childDivision;
  }

  public void setChildDivision(Division childDivision) {
    this.childDivision = childDivision;
  }

}
