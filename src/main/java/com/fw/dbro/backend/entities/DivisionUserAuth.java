package com.fw.dbro.backend.entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import static com.fw.dbro.backend.constants.MessageConstants.*;

@Table(name = "division_user_auth")
@Entity
public class DivisionUserAuth extends BaseEntity {

  @JoinColumn(name = "division_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = DIVISION_NOT_NULL_MESSAGE)
  private Division division;

  @JoinColumn(name = "user_auth_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = USER_AUTH_NOT_NULL_MESSAGE)
  private UserAuth userAuth;

  @OneToMany(mappedBy = "divisionUserAuth")
  @LazyCollection(LazyCollectionOption.FALSE)
  private List<UserAttendance> userAttendances;

  public Division getDivision() {
    return division;
  }

  public void setDivision(Division division) {
    this.division = division;
  }

  public UserAuth getUserAuth() {
    return userAuth;
  }

  public void setUserAuth(UserAuth userAuth) {
    this.userAuth = userAuth;
  }

  public List<UserAttendance> getUserAttendances() {
    return userAttendances;
  }

  public void setUserAttendances(List<UserAttendance> userAttendances) {
    this.userAttendances = userAttendances;
  }

}
