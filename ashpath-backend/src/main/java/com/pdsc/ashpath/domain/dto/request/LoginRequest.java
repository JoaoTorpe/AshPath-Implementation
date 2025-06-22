package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.NotNull;

public class LoginRequest
{
  @NotNull(message = "'email' field is required.")
  private String email;

  @NotNull(message = "'password' field is required.")
  private String password;

  public LoginRequest()
  {}

  public LoginRequest(String email, String password)
  {
    this.email = email;
    this.password = password;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPassword()
  {
    return this.password;
  }
}
