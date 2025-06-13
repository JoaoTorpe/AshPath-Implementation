package com.pdsc.ashpath.domain.dto.request;

import java.time.LocalDate;

public class CreateDeceasedRequest
{
  private String fullname;
  private LocalDate birthDate;
  private LocalDate deathDate;
  private String causeOfDeath;
  private String fatherName;
  private String motherName;
  private Long graveID;

  public CreateDeceasedRequest()
  {}

  public CreateDeceasedRequest(String fullname, LocalDate birthDate, LocalDate deathDate, String causeOfDeath, String fatherName, String motherName, Long graveID)
  {
    this.fullname = fullname;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
    this.causeOfDeath = causeOfDeath;
    this.fatherName = fatherName;
    this.motherName = motherName;
    this.graveID = graveID;
  }
  {
    this.fullname = fullname;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
    this.causeOfDeath = causeOfDeath;
    this.fatherName = fatherName;
    this.motherName = motherName;
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

    public Long getGraveID()
    {
        return graveID;
    }
    public void setGraveID(Long graveID)
    {
        this.graveID = graveID;
        }


}
