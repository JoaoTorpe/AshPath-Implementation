package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.pdsc.ashpath.domain.entity.CremationEntry;
import com.pdsc.ashpath.domain.entity.Deceased;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CremationEntryResponse
{
  private Long id;
  private LocalDateTime creationDate;
  private NecrotomistUserResponse necrotomist;
  private List<DeceasedResponse> deceaseds = new ArrayList<>();

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
}
