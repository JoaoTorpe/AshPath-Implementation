package com.pdsc.ashpath.domain.dto.response;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.entity.Grave;

public final class GraveResponse
{
  private Long id;
  private String location;
  private List<DeceasedResponse> deceaseds = new ArrayList<>();

  public GraveResponse()
  {}

  public GraveResponse(Long id, String location, List<DeceasedResponse> deceaseds)
  {
    this.id = id;
    this.location = location;
    this.deceaseds = deceaseds;
  }

  public GraveResponse(Grave grave)
  {
    this.setId(grave.getId());
    this.setLocation(grave.getLocation());

    Iterator<Deceased> iterator = grave.getDeceasedSet().iterator();
    while (iterator.hasNext())
    {
      DeceasedResponse deceased = new DeceasedResponse(iterator.next());
      this.deceaseds.add(deceased);
    }
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

  public void setDeceaseds(List<DeceasedResponse> deceaseds)
  {
    this.deceaseds = deceaseds;
  }

  public List<DeceasedResponse> getDeceaseds()
  {
    return this.deceaseds;
  }
}
