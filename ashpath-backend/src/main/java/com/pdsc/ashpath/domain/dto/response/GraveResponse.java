package com.pdsc.ashpath.domain.dto.response;

import com.pdsc.ashpath.domain.entity.Grave;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GraveResponse
{
  private Long id;
  private String location;
  private DeceasedResponse deceased;

  public GraveResponse(Grave grave)
  {
    this.setId(grave.getId());
    this.setLocation(grave.getLocation());

    if (grave.getDeceased() != null)
      this.setDeceased(new DeceasedResponse(grave.getDeceased()));
  }
}
