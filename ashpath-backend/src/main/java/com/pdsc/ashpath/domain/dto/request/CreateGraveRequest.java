package com.pdsc.ashpath.domain.dto.request;

public class CreateGraveRequest
{
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
