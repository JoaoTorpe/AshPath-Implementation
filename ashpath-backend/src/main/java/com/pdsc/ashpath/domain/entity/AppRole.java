package com.pdsc.ashpath.domain.entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.pdsc.ashpath.domain.enums.UserAppRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "APP_ROLE")
@Getter
@Setter
public class AppRole
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "Name", unique = true, nullable = false)
  private UserAppRole name;

  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "appRoleSet")
  private Set<User> usersSet = new HashSet<>();

  public void addUser(User user)
  {
    usersSet.add(user);
    user.getAppRoleSet().add(this);
  }

  @Override
  public int hashCode()
  {
    int hash = 5;
    hash = 97 * hash + Objects.hashCode(this.id);
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

    final AppRole other = (AppRole) obj;
    return Objects.equals(this.id, other.id);
  }
}
