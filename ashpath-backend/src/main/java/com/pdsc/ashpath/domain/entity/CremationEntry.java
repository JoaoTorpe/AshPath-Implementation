package com.pdsc.ashpath.domain.entity;

import java.time.LocalDateTime;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CREMATION_ENTRY")
@NoArgsConstructor
@Getter
@Setter
public class CremationEntry
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;

  @Column(name = "EnteredDate", columnDefinition = "TIMESTAMP")
  private LocalDateTime creationDate;

  @ManyToOne
  @JoinColumn(name = "Necrotomist_Id")
  private User necrotomist;

  @OneToMany(mappedBy = "cremationEntry")
  private Set<Deceased> deceasedSet;

  public void addDeceased(Deceased deceased)
  {
    this.deceasedSet.add(deceased);
    deceased.setCremationEntry(this);
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
