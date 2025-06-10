package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.User;

public final class ViewerUserResponse
{
  private Long id;
  private String email;
  private String fullname;
  private LocalDateTime registrationDate;
  private LocalDateTime lastActivityDate;

  public ViewerUserResponse()
  {}

  public ViewerUserResponse(Long id, String email, String fullname, LocalDateTime registrationDate, LocalDateTime lastActivityDate)
  {
    this.id = id;
    this.email = email;
    this.fullname = fullname;
    this.registrationDate = registrationDate;
    this.lastActivityDate = lastActivityDate;
  }

  public ViewerUserResponse(User user)
  {
    setId(user.getId());
    setEmail(user.getEmail());
    setFullname(user.getFullname());
    setRegistrationDate(user.getRegistrationDate());
    setLastActivityDate(user.getLastActivityDate());
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setFullname(String fullname)
  {
    this.fullname = fullname;
  }

  public String getFullname()
  {
    return this.fullname;
  }

  public void setRegistrationDate(LocalDateTime registrationDate)
  {
    this.registrationDate = registrationDate;
  }

  public LocalDateTime getRegistrationDate()
  {
    return this.registrationDate;
  }

  public void setLastActivityDate(LocalDateTime lastActivityDate)
  {
    this.lastActivityDate = lastActivityDate;
  }

  public LocalDateTime getLastActivityDate()
  {
    return this.lastActivityDate;
  }
}
