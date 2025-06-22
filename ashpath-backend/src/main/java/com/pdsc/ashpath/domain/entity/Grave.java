package com.pdsc.ashpath.domain.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "GRAVE")
public class Grave
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotNull(message = "'location' field on 'Grave' entity cannot be null.")
  @Size(min = 3, max = 128, message = "'location' field on 'Grave' entity must have a minimum of 3 characters and a maximum of 128 characters.")
  @Column(name = "LOCATION", length = 128)
  private String location;

  @OneToMany(mappedBy = "grave")
  private Set<Deceased> deceasedSet = new HashSet<>();

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

  public void setDeceasedSet(Set<Deceased> deceasedSet)
  {
    this.deceasedSet = deceasedSet;
  }

  public Set<Deceased> getDeceasedSet()
  {
    return this.deceasedSet;
  }

  public void addDeceased(Deceased deceased)
  {
    this.deceasedSet.add(deceased);
    deceased.setGrave(this);
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
