package com.pdsc.ashpath.domain.dto.response;

import java.util.Set;

import com.pdsc.ashpath.domain.entity.AppRole;
import com.pdsc.ashpath.domain.entity.User;

public final class SuccessfulLoginResponse {
  private Long loggedUserId;
  private Set<AppRole> appRoleSet;

  public SuccessfulLoginResponse() {
  }

  public SuccessfulLoginResponse(Long loggedUserId) {
    this.loggedUserId = loggedUserId;
  }

  public SuccessfulLoginResponse(User user) {
    setLoggedUserId(user.getId());
    setAppRoleSet(user.getAppRoleSet());
  }

  public void setLoggedUserId(Long loggedUserId) {
    this.loggedUserId = loggedUserId;
  }

  public Long getLoggedUserId() {
    return loggedUserId;
  }

  public Set<AppRole> getAppRoleSet() {
    return appRoleSet;
  }

  public void setAppRoleSet(Set<AppRole> appRoleSet) {
    this.appRoleSet = appRoleSet;
  }
}
