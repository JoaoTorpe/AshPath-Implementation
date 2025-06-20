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
}
