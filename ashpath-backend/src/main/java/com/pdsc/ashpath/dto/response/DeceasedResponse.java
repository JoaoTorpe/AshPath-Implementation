package com.pdsc.ashpath.dto.response;

import java.time.LocalDate;

import com.pdsc.ashpath.domain.entity.Deceased;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeceasedResponse {

  private Long id;
  private String fullname;
  private String causeOfDeath;
  private LocalDate birthDate;
  private LocalDate deathDate;

  public DeceasedResponse(Deceased deceased)
  {
    setId(deceased.getId());
    setFullname(deceased.getFullname());
    setCauseOfDeath(deceased.getCauseOfDeath());
    setBirthDate(deceased.getBirthDate());
    setDeathDate(deceased.getDeathDate());
  }
}
