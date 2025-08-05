package com.pdsc.ashpath.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateGraveRequest
{
  @NotNull(message = "{NotNull.grave.location}")
  @Size(min = 3, max = 128, message = "{Size.grave.location}")
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
