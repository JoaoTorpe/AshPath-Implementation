package com.pdsc.ashpath.domain.dto.response;

import com.pdsc.ashpath.domain.entity.Grave;

public final class GraveResponse
{
  private Long id;
  private String location;
  private DeceasedResponse deceased;

  public GraveResponse()
  {}

  public GraveResponse(Long id, String location, DeceasedResponse deceased)
  {
    this.id = id;
    this.location = location;
    this.deceased = deceased;
  }

  public GraveResponse(Grave grave)
  {
    this.setId(grave.getId());
    this.setLocation(grave.getLocation());

    if (grave.getDeceased() != null)
      this.setDeceased(new DeceasedResponse(grave.getDeceased()));
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getLocation()
  {
    return this.location;
  }

  public void setDeceased(DeceasedResponse deceased)
  {
    this.deceased = deceased;
  }

  public DeceasedResponse getDeceased()
  {
    return this.deceased;
  }
}
