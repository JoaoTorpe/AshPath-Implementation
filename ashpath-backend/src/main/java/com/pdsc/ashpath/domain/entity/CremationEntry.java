package com.pdsc.ashpath.domain.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CREMATION_ENTRY")
public class CremationEntry
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "ENTERED_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime creationDate;

  @ManyToOne
  @JoinColumn(name = "NECROTOMIST_ID")
  private User necrotomist;

  @OneToMany(mappedBy = "cremationEntry")
  private Set<Deceased> deceasedSet = new HashSet<>();

  public CremationEntry()
  {}

  public void addDeceased(Deceased deceased)
  {
    this.deceasedSet.add(deceased);
    deceased.setCremationEntry(this);
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setCreationDate(LocalDateTime creationDate)
  {
    this.creationDate = creationDate;
  }

  public LocalDateTime getCreationDate()
  {
    return this.creationDate;
  }

  public void setNecrotomist(User necrotomist)
  {
    this.necrotomist = necrotomist;
  }

  public User getNecrotomist()
  {
    return this.necrotomist;
  }

  public void setDeceasedSet(Set<Deceased> deceasedSet)
  {
    this.deceasedSet = deceasedSet;
  }

  public Set<Deceased> getDeceasedSet()
  {
    return this.deceasedSet;
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;

    if (obj == null || getClass() != obj.getClass())
      return false;
    
    CremationEntry other = (CremationEntry) obj;
    return Objects.equals(this.id, other.id);
  }
}
