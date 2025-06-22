package com.pdsc.ashpath.domain.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public abstract class AbstractCreateUserRequest
{
  @NotNull(message = "'email' field is required.")
  @Size(min = 3, max = 254, message = "'email' field must have a minimum of 3 characters and a maximum of 254 characters.")
  @Email(message = "'email' field must be a valid email.")
  protected String email;

  @NotNull(message = "'password' field is required.")
  @Size(min = 6, max = 12, message = "'password' field must have a minimum of 6 characters and a maximum of 12 characters.")
  @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+_={}\\[\\]|:;\"'<>,.?/~`]).+$",
    message = "'password' field must include 1 number, 1 uppercase letter, and 1 special character."
  )
  protected String password;

  @NotNull(message = "'fullname' field is required.")
  @Size(min = 3, max = 64, message = "'fullname' field must have a minimum of 3 characters and a maximum of 64 characteres.")
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
