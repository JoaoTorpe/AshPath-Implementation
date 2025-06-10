package com.pdsc.ashpath.domain.dto.request.user;

public class CreateAdminUserRequest extends AbstractCreateUserRequest
{
  public CreateAdminUserRequest()
  {}

  public CreateAdminUserRequest(String email, String password, String fullname)
  {
    this.email = email;
    this.password = password;
    this.fullname = fullname;
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

  public void setFullname(String fullname)
  {
    this.fullname = fullname;
  }

  public String getFullname()
  {
    return this.fullname;
  }
}
