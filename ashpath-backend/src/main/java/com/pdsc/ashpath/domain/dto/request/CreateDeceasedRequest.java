package com.pdsc.ashpath.domain.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class CreateDeceasedRequest
{
  @NotNull(message = "{NotNull.deceased.fullname}")
  @Size(min = 3, max = 128, message = "{Size.deceased.fullname}")
  private String fullname;

  @NotNull(message = "{NotNull.deceased.birthDate}")
  @Past(message = "{Past.deceased.birthDate}")
  private LocalDate birthDate;

  @NotNull(message = "{NotNull.deceased.deathDate}")
  @PastOrPresent(message = "{PastOrPresent.deceased.deathDate}")
  private LocalDate deathDate;

  @NotNull(message = "{NotNull.deceased.causeOfDeath}")
  @Size(min = 3, max = 128, message = "{Size.deceased.causeOfDeath}")
  private String causeOfDeath;

  @NotNull(message = "{NotNull.deceased.fatherName}")
  @Size(min = 3, max = 128, message = "{Size.deceased.fatherName}")
  private String fatherName;

  @NotNull(message = "{NotNull.deceased.motherName}")
  @Size(min = 3, max = 128, message = "{Size.deceased.motherName}")
  private String motherName;

  @Min(value = 1, message = "{Min.deceased.graveID}")
  private Long graveID;

  private Long cremationEntryID;

  public CreateDeceasedRequest() {
  }

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

  public String getFullname() {
    return this.fullname;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getBirthDate() {
    return this.birthDate;
  }

  public void setDeathDate(LocalDate deathDate) {
    this.deathDate = deathDate;
  }

  public LocalDate getDeathDate() {
    return this.deathDate;
  }

  public void setCauseOfDeath(String causeOfDeath) {
    this.causeOfDeath = causeOfDeath;
  }

  public String getCauseOfDeath() {
    return this.causeOfDeath;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getFatherName() {
    return this.fatherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public String getMotherName() {
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

  public void setCremationEntryID(Long cremationEntryID)
  {
    this.cremationEntryID = cremationEntryID;
  }

  public Long getCremationEntryID()
  {
    return this.cremationEntryID;
  }
}
