package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pdsc.ashpath.domain.entity.CremationEntry;
import com.pdsc.ashpath.domain.entity.Deceased;

public final class CremationEntryResponse
{
  private Long id;
  private LocalDateTime creationDate;
  private NecrotomistUserResponse necrotomist;
  private List<DeceasedResponse> deceaseds = new ArrayList<>();

  public CremationEntryResponse()
  {}

  public CremationEntryResponse(Long id, LocalDateTime creationDate, NecrotomistUserResponse necrotomist, List<DeceasedResponse> deceaseds)
  {
    this.id = id;
    this.creationDate = creationDate;
    this.necrotomist = necrotomist;
    this.deceaseds = deceaseds;
  }

  public CremationEntryResponse(CremationEntry cremationEntry)
  {
    setId(cremationEntry.getId());
    setCreationDate(cremationEntry.getCreationDate());
    setNecrotomist(new NecrotomistUserResponse(cremationEntry.getNecrotomist()));
    
    Iterator<Deceased> iterator = cremationEntry.getDeceasedSet().iterator();
    while (iterator.hasNext())
    {
      DeceasedResponse deceased = new DeceasedResponse(iterator.next());
      deceaseds.add(deceased);
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

  public void setCreationDate(LocalDateTime creationDate)
  {
    this.creationDate = creationDate;
  }

  public LocalDateTime getCreationDate()
  {
    return this.creationDate;
  }

  public void setNecrotomist(NecrotomistUserResponse necrotomist)
  {
    this.necrotomist = necrotomist;
  }

  public NecrotomistUserResponse getNecrotomist()
  {
    return this.necrotomist;
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
