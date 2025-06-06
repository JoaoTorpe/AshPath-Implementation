package com.pdsc.ashpath.domain.dto.response;

import com.pdsc.ashpath.domain.entity.User;

public final class SuccessfulLoginResponse
{
  private Long loggedUserId;

  public SuccessfulLoginResponse()
  {}

  public SuccessfulLoginResponse(Long loggedUserId)
  {
    this.loggedUserId = loggedUserId;
  }  

  public SuccessfulLoginResponse(User user)
  {
    setLoggedUserId(user.getId());
  }

  public void setLoggedUserId(Long loggedUserId)
  {
    this.loggedUserId = loggedUserId;
  }

  public Long getLoggedUserId()
  {
    return loggedUserId;
  }
}
