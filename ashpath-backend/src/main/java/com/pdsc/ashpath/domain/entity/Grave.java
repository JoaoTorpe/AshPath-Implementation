package com.pdsc.ashpath.domain.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GRAVE")
public class Grave
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;

  @Column(name = "Location", length = 128)
  private String location;

  @OneToOne(mappedBy = "grave")
  private Deceased deceased;

  public Grave()
  {}

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public String getLocation()
  {
    return this.location;
  }

  public void setDeceased(Deceased deceased)
  {
    this.deceased = deceased;
  }

  public Deceased getDeceased()
  {
    return this.deceased;
  }

  @Override
  public int hashCode()
  {
    int hash = 3;
    hash = 11 * hash + Objects.hashCode(this.id);
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

    final Grave other = (Grave) obj;
    return Objects.equals(this.id, other.id);
  }
}
