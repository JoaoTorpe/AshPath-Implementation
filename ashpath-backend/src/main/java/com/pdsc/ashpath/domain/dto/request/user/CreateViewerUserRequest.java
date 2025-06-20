package com.pdsc.ashpath.domain.dto.request.user;

public class CreateViewerUserRequest extends AbstractCreateUserRequest
{
  public CreateViewerUserRequest()
  {}

  public CreateViewerUserRequest(String email, String password, String fullname)
  {
    this.email = email;
    this.password = password;
    this.fullname = fullname;
  }
}
