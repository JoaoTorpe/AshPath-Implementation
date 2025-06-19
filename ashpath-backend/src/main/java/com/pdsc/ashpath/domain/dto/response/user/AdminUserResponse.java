package com.pdsc.ashpath.domain.dto.response.user;

import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.User;

public final class AdminUserResponse extends AbstractUserReponse
{
  public AdminUserResponse()
  {}

  public AdminUserResponse(Long id, String email, String fullname, LocalDateTime registrationDate, LocalDateTime lastActivityDate)
  {
    this.id = id;
    this.email = email;
    this.fullname = fullname;
    this.registrationDate = registrationDate;
    this.lastActivityDate = lastActivityDate;
  }

  public AdminUserResponse(User user)
  {
    setId(user.getId());
    setEmail(user.getEmail());
    setFullname(user.getFullname());
    setRegistrationDate(user.getRegistrationDate());
    setLastActivityDate(user.getLastActivityDate());
  }
}
