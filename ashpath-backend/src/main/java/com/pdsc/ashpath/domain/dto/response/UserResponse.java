package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.User;

public final class UserResponse
{
  private Long id;
  private String email;
  private String fullname;
  private LocalDateTime registrationDate;
  private LocalDateTime lastActivityDate;
  private String specialization;

  public UserResponse()
  {}

  public UserResponse(Long id, String email, String fullname, LocalDateTime registrationDate, LocalDateTime lastActivityDate, String specialization)
  {
    this.id = id;
    this.email = email;
    this.fullname = fullname;
    this.registrationDate = registrationDate;
    this.lastActivityDate = lastActivityDate;
    this.specialization = specialization;
  }

  public UserResponse(User user)
  {
    setId(user.getId());
    setEmail(user.getEmail());
    setFullname(user.getFullname());
    setRegistrationDate(user.getRegistrationDate());
    setLastActivityDate(user.getLastActivityDate());
    setSpecialization(user.getSpecialization());
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return id;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmail()
  {
    return email;
  }

  public void setFullname(String fullname)
  {
    this.fullname = fullname;
  }

  public String getFullname()
  {
    return fullname;
  }

  public void setRegistrationDate(LocalDateTime registrationDate)
  {
    this.registrationDate = registrationDate;
  }

  public LocalDateTime getRegistrationDate()
  {
    return registrationDate;
  }

  public void setLastActivityDate(LocalDateTime lastActivityDate)
  {
    this.lastActivityDate = lastActivityDate;
  }

  public LocalDateTime getLastActivityDate()
  {
    return lastActivityDate;
  }

  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }

  public String getSpecialization()
  {
    return specialization;
  }
}
