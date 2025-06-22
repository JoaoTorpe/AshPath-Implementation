package com.pdsc.ashpath.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.pdsc.ashpath.domain.enums.DeceasedStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "DECEASED")
public class Deceased
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotNull(message = "'fullname' field on 'Deceased' entity cannot be null.")
  @Size(min = 3, max = 128, message = "'fullname' field on 'Deceased' entity must have a minimum of 3 characters and a maximum of 128 characters.")
  @Column(name = "FULL_NAME", length = 128)
  private String fullname;

  @NotNull(message = "'birthDate' field on 'Deceased' entity cannot be null.")
  @Past(message = "'birthDate' field on 'Deceased' entity must be earlier than current date.")
  @Column(name = "BIRTH_DATE", columnDefinition = "DATE")
  private LocalDate birthDate;

  @NotNull(message = "'deathDate' field on 'Deceased' entity cannot be null.")
  @PastOrPresent(message = "'deathDate' field on 'Deceased' entity must be earlier or equals than current date.")
  @Column(name = "DEATH_DATE", columnDefinition = "DATE")
  private LocalDate deathDate;

  @NotNull(message = "'causeOfDeath' field on 'Deceased' entity cannot be null.")
  @Size(min = 3, max = 128, message = "'causeOfDeath' field on 'Deceased' entity must have a minimum of 3 characters and a maximum of 128 characters.")
  @Column(name = "CAUSE_OF_DEATH", length = 128)
  private String causeOfDeath;

  @Lob
  private byte[] deathCertificate;

  @NotNull(message = "'fatherName' field on 'Deceased' entity cannot be null.")
  @Size(min = 3, max = 128, message = "'fatherName' field on 'Deceased' entity must have a minimum of 3 characters and a maximum of 128 characters.")
  @Column(name = "FATHER_NAME", length = 128)
  private String fatherName;

  @NotNull(message = "'motherName' field on 'Deceased' entity cannot be null.")
  @Size(min = 3, max = 128, message = "'motherName' field on 'Deceased' entity must have a minimum of 3 characters and a maximum of 128 characters.")
  @Column(name = "MOTHER_NAME", length = 128)
  private String motherName;

  @Enumerated(EnumType.STRING)
  @Column(name = "STATUS")
  private DeceasedStatus status;

  @PastOrPresent(message = "'cremationEnteredDate' field on 'Deceased' entity must be earlier or equals than current date.")
  @Column(name = "CREMATION_ENTERED_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime cremationEnteredDate;

  @ManyToOne
  @JoinColumn(name = "CREMATION_ENTRY_ID")
  private CremationEntry cremationEntry;

  @ManyToOne
  @JoinColumn(name = "GRAVE_ID", nullable = true)
  private Grave grave;

  public Deceased()
  {}

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

  public void setDeathCertificate(byte[] deathCertificate)
  {
    this.deathCertificate = deathCertificate;
  }

  public byte[] getDeathCertificate()
  {
    return this.deathCertificate;
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

  public void setCremationEntry(CremationEntry cremationEntry)
  {
    this.cremationEntry = cremationEntry;
  }

  public CremationEntry getCremationEntry()
  {
    return this.cremationEntry;
  }

  public void setGrave(Grave grave)
  {
    this.grave = grave;
  }

  public Grave getGrave()
  {
    return this.grave;
  }

  @Override
  public int hashCode()
  {
    int hash = 7;
    hash = 79 * hash + Objects.hashCode(this.id);
    return hash;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }

    final Deceased other = (Deceased) obj;
    return Objects.equals(this.id, other.id);
  }

  @Override
  public String toString() {
    return "Deceased [id=" + id + ", fullname=" + fullname + ", birthDate=" + birthDate + ", deathDate=" + deathDate
        + ", fatherName=" + fatherName + ", motherName=" + motherName + "]";
  }
}
