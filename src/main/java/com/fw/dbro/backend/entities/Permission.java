package com.fw.dbro.backend.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import static com.fw.dbro.backend.constants.MessageConstants.*;

@Table(name = "permission")
@Entity
public class Permission extends BaseEntity {

  @JoinColumn(name = "role_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = ROLE_NOT_NULL_MESSAGE)
  private Sysconfig role;

  @JoinColumn(name = "api_path_id", referencedColumnName = "id")
  @ManyToOne
  @NotNull(message = API_PATH_NOT_NULL_MESSAGE)
  private Sysconfig apiPath;

  public Sysconfig getRole() {
    return role;
  }

  public void setRole(Sysconfig role) {
    this.role = role;
  }

  public Sysconfig getApiPath() {
    return apiPath;
  }

  public void setApiPath(Sysconfig apiPath) {
    this.apiPath = apiPath;
  }

}
