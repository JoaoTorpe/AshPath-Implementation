package com.pdsc.ashpath.domain.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class CreateDeceasedRequest
{
  @NotNull(message = "'fullname' field is required.")
  @Size(min = 3, max = 128, message = "'fullname' field must have a minimum of 3 characters and a maximum of 128 characters.")
  private String fullname;

  @NotNull(message = "'birthDate' field is required.")
  @Past(message = "'birthDate' field must be earlier than current date.")
  private LocalDate birthDate;

  @NotNull(message = "'deathDate' field is required.")
  @PastOrPresent(message = "'deathDate' must be earlier or equals than current date.")
  private LocalDate deathDate;

  @NotNull(message = "'causeOfDeath' field is required.")
  @Size(min = 3, max = 128, message = "'causeOfDeath' field must have a minimum of 3 characters and a maximum of 128 characters.")
  private String causeOfDeath;

  @NotNull(message = "'fatherName' field is required.")
  @Size(min = 3, max = 128, message = "'fatherName' field must have a minimum of 3 characters and a maximum of 128 characters.")
  private String fatherName;

  @NotNull(message = "'motherName' field is required.")
  @Size(min = 3, max = 128, message = "'motherName' field must have a minimum of 3 characters and a maximum of 128 characters.")
  private String motherName;

  @Min(value = 1, message = "IDs values must be greater or equals than 1.")
  private Long graveID;

  public CreateDeceasedRequest()
  {}

  public CreateDeceasedRequest(String fullname, LocalDate birthDate, LocalDate deathDate, String causeOfDeath, String fatherName, String motherName)
  {
    this.fullname = fullname;
    this.birthDate = birthDate;
    this.deathDate = deathDate;
    this.causeOfDeath = causeOfDeath;
    this.fatherName = fatherName;
    this.motherName = motherName;
  }

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
