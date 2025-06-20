package com.pdsc.ashpath.domain.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public abstract class AbstractCreateUserRequest
{
  @NotBlank(message = "'email' field is required.")
  @Size(max = 64, message = "'email' field must have a maximum of 64 characters.")
  @Email(message = "'email' field must be a valid email.")
  protected String email;

  @Size(min = 6, max = 64, message = "'password' field must have a minimum of 6 characters and a maximum of 64 characters.")
  @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+_={}\\[\\]|:;\"'<>,.?/~`]).+$",
    message = "'password' field must include 1 number, 1 uppercase letter, and 1 special character."
  )
  protected String password;

  @Size(min = 3, max = 128, message = "'fullname' field must have a minimum of 3 characters and a maximum of 128 characteres.")
  protected String fullname;

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getFullname()
  {
    return fullname;
  }

  public void setFullname(String fullname)
  {
    this.fullname = fullname;
  }
}
