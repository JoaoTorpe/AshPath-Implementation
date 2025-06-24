package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateGraveRequest
{
  @NotNull(message = "'location' field is required.")
  @Size(min = 3, max = 128, message = "'location' field must have a minimum of 3 characters and a maximum of 128 characters.")
  private String location;

  public CreateGraveRequest()
  {}

  public CreateGraveRequest(String location)
  {
    this.location = location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getLocation()
  {
    return this.location;
  }
}
