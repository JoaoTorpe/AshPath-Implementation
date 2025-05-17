package com.pdsc.ashpath.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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

  @Column(name = "EnteredDate", columnDefinition = "DATE")
  private LocalDate enteredDate;


  @Column(name = "CREMATION_COMPLETED")
  private Boolean completed;

  @OneToOne
  @JoinColumn(name = "Deceased_Id")
  private Deceased deceased;

  @ManyToOne
  @JoinColumn(name = "Necrotomist_Id")
  private User necrotomist;

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
