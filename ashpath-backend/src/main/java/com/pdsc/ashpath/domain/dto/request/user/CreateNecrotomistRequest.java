package com.pdsc.ashpath.domain.dto.request.user;

import jakarta.validation.constraints.Size;

public class CreateNecrotomistRequest extends AbstractCreateUserRequest
{
  @Size(min = 3, max = 64, message = "'specialization' field must have a minimum of 3 characters and a maximum of 64 characters.")
  private String specialization;

  public CreateNecrotomistRequest()
  {}

  public CreateNecrotomistRequest(String email, String password, String fullname, String specialization)
  {
    this.email = email;
    this.password = password;
    this.fullname = fullname;
    this.specialization = specialization;
  }

  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }

  public String getSpecialization()
  {
    return this.specialization;
  }
}
