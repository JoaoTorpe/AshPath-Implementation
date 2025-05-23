package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.enums.DeceasedStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeceasedResponse
{
  private Long id;
  private String fullname;
  private LocalDate birthDate;
  private LocalDate deathDate;
  private String causeOfDeath;
  private String deathCertificateDownloadLink;
  private String fatherName;
  private String motherName;
  private DeceasedStatus status;
  private LocalDateTime cremationEnteredDate;

  public DeceasedResponse(Deceased deceased)
  {
    setId(deceased.getId());
    setFullname(deceased.getFullname());
    setBirthDate(deceased.getBirthDate());
    setDeathDate(deceased.getDeathDate());
    setCauseOfDeath(deceased.getCauseOfDeath());
    setFatherName(deceased.getFatherName());
    setMotherName(deceased.getMotherName());
    setStatus(deceased.getStatus());
    setCremationEnteredDate(deceased.getCremationEnteredDate());
  }
}
