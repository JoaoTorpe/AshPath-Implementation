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
@Table(name = "CREMATION_QUEUE")
@NoArgsConstructor
@Getter
@Setter
public class CremationQueue
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

  @OneToMany(mappedBy = "cremationQueue")
  private Set<Deceased> deceasedSet;

  public void addDeceased(Deceased deceased)
  {
    this.deceasedSet.add(deceased);
    deceased.setCremationQueue(this);
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
    
    CremationQueue other = (CremationQueue) obj;
    return Objects.equals(this.id, other.id);
  }
}
