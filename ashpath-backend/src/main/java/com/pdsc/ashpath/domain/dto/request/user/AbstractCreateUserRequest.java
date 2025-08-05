package com.pdsc.ashpath.domain.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public abstract class AbstractCreateUserRequest
{
  @NotNull(message = "{NotNull.user.email}")
  @Size(min = 3, max = 254, message = "{Size.user.email}")
  @Email(message = "{Email.user.email}")
  protected String email;

  @NotNull(message = "{NotNull.user.password}")
  @Size(min = 6, max = 12, message = "{Size.user.password}")
  @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+_={}\\[\\]|:;\"'<>,.?/~`]).+$",
    message = "{Pattern.user.password}"
  )
  protected String password;

  @NotNull(message = "{NotNull.user.fullname}")
  @Size(min = 3, max = 64, message = "{Size.user.fullname}")
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
