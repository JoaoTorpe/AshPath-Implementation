package com.pdsc.ashpath.domain.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pdsc.ashpath.domain.entity.Deceased;
import com.pdsc.ashpath.domain.enums.DeceasedStatus;

public final class DeceasedResponse {
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
  private String graveLocation;

  // Construtores
  public DeceasedResponse() {}

  public DeceasedResponse(
          Long id, String fullname, LocalDate birthDate, LocalDate deathDate,
          String causeOfDeath, String deathCertificateDownloadLink,
          String fatherName, String motherName, DeceasedStatus status,
          LocalDateTime cremationEnteredDate, Long cremationEntryId,
          String graveLocation
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
    this.graveLocation = graveLocation;
  }

  public DeceasedResponse(Deceased deceased) {
    this.id = deceased.getId();
    this.fullname = deceased.getFullname();
    this.birthDate = deceased.getBirthDate();
    this.deathDate = deceased.getDeathDate();
    this.causeOfDeath = deceased.getCauseOfDeath();
    this.fatherName = deceased.getFatherName();
    this.motherName = deceased.getMotherName();
    this.status = deceased.getStatus();
    this.cremationEnteredDate = deceased.getCremationEnteredDate();

    if (deceased.getCremationEntry() != null) {
      this.cremationEntryId = deceased.getCremationEntry().getId();
    }

    if (deceased.getGrave() != null) {
      this.graveLocation = deceased.getGrave().getLocation();
    }
  }

  // Getters e Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public LocalDate getDeathDate() {
    return deathDate;
  }

  public void setDeathDate(LocalDate deathDate) {
    this.deathDate = deathDate;
  }

  public String getCauseOfDeath() {
    return causeOfDeath;
  }

  public void setCauseOfDeath(String causeOfDeath) {
    this.causeOfDeath = causeOfDeath;
  }

  public String getDeathCertificateDownloadLink() {
    return deathCertificateDownloadLink;
  }

  public void setDeathCertificateDownloadLink(String deathCertificateDownloadLink) {
    this.deathCertificateDownloadLink = deathCertificateDownloadLink;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }

  public DeceasedStatus getStatus() {
    return status;
  }

  public void setStatus(DeceasedStatus status) {
    this.status = status;
  }

  public LocalDateTime getCremationEnteredDate() {
    return cremationEnteredDate;
  }

  public void setCremationEnteredDate(LocalDateTime cremationEnteredDate) {
    this.cremationEnteredDate = cremationEnteredDate;
  }

  public Long getCremationEntryId() {
    return cremationEntryId;
  }

  public void setCremationEntryId(Long cremationEntryId) {
    this.cremationEntryId = cremationEntryId;
  }

  public String getGraveLocation() {
    return graveLocation;
  }

  public void setGraveLocation(String graveLocation) {
    this.graveLocation = graveLocation != null ? graveLocation.trim() : null;
  }

  // Métodos adicionais recomendados
  @Override
  public String toString() {
    return "DeceasedResponse{" +
            "id=" + id +
            ", fullname='" + fullname + '\'' +
            ", graveLocation='" + graveLocation + '\'' +
            ", status=" + status +
            '}';
  }
}