package com.pdsc.ashpath.domain.dto.request;

public class CreateNecrotomistRequest
{
  private String email;
  private String password;
  private String fullname;
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

  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }

  public String getSpecialization()
  {
    return this.specialization;
  }
}
