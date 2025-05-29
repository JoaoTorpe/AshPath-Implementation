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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DECEASED")
@NoArgsConstructor
@Getter
@Setter
public class Deceased
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;

  @Column(name = "Fullname", length = 128)
  private String fullname;

  @Column(name = "BirthDate", columnDefinition = "DATE")
  private LocalDate birthDate;

  @Column(name = "DeathDate", columnDefinition = "DATE")
  private LocalDate deathDate;

  @Column(name = "CauseOfDeath", length = 128)
  private String causeOfDeath;

  @Lob
  private byte[] deathCertificate;

  @Column(name = "FatherName", length = 128)
  private String fatherName;

  @Column(name = "MotherName", length = 128)
  private String motherName;

  @Enumerated(EnumType.STRING)
  @Column(name = "Status")
  private DeceasedStatus status;

  @Column(name = "CremationEnteredDate", columnDefinition = "TIMESTAMP")
  private LocalDateTime cremationEnteredDate;

  @ManyToOne
  @JoinColumn(name = "CremationEntryId")
  private CremationEntry cremationEntry;

  @OneToOne
  @JoinColumn(name = "GraveId", nullable = true)
  private Grave grave;

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
