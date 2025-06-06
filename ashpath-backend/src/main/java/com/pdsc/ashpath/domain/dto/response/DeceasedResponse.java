package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.enums.DeceasedStatus;

public final class DeceasedResponse
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
  private Long cremationEntryId;

  public DeceasedResponse()
  {}

  public DeceasedResponse(
    Long id, String fullname, LocalDate birthDate, LocalDate deathDate, String causeOfDeath,
    String deathCertificateDownloadLink, String fatherName, String motherName, DeceasedStatus status,
    LocalDateTime cremationEnteredDate, Long cremationEntryId
  ) {
    this.id = id;
    this.fullname = fullname;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
    this.causeOfDeath = causeOfDeath;
    this.deathCertificateDownloadLink = deathCertificateDownloadLink;
    this.fatherName = fatherName;
    this.motherName = motherName;
    this.status = status;
    this.cremationEnteredDate = cremationEnteredDate;
    this.cremationEntryId = cremationEntryId;
  }

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
    
    if (deceased.getCremationEntry() != null)
      setCremationEntryId(deceased.getCremationEntry().getId());
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setFullname(String fullname)
  {
    this.fullname = fullname;
  }

  public String getFullname()
  {
    return this.fullname;
  }

  public void setBirthDate(LocalDate birthDate)
  {
    this.birthDate = birthDate;
  }

  public LocalDate getBirthDate()
  {
    return this.birthDate;
  }

  public void setDeathDate(LocalDate deathDate)
  {
    this.deathDate = deathDate;
  }

  public LocalDate getDeathDate()
  {
    return this.deathDate;
  }

  public void setCauseOfDeath(String causeOfDeath)
  {
    this.causeOfDeath = causeOfDeath;
  }

  public String getCauseOfDeath()
  {
    return this.causeOfDeath;
  }

  public void setDeathCertificateDownloadLink(String deathCertificateDownloadLink)
  {
    this.deathCertificateDownloadLink = deathCertificateDownloadLink;
  }

  public String getDeathCertificateDownloadLink()
  {
    return this.deathCertificateDownloadLink;
  }

  public void setFatherName(String fatherName)
  {
    this.fatherName = fatherName;
  }

  public String getFatherName()
  {
    return this.fatherName;
  }

  public void setMotherName(String motherName)
  {
    this.motherName = motherName;
  }

  public String getMotherName()
  {
    return this.motherName;
  }

  public void setStatus(DeceasedStatus status)
  {
    this.status = status;
  }

  public DeceasedStatus getStatus()
  {
    return this.status;
  }

  public void setCremationEnteredDate(LocalDateTime cremationEnteredDate)
  {
    this.cremationEnteredDate = cremationEnteredDate;
  }

  public LocalDateTime getCremationEnteredDate()
  {
    return this.cremationEnteredDate;
  }

  public void setCremationEntryId(Long cremationEntryId)
  {
    this.cremationEntryId = cremationEntryId;
  }

  public Long getCremationEntryId()
  {
    return this.cremationEntryId;
  }
}
