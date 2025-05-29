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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "APP_USER")
@NoArgsConstructor
@Getter
@Setter
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Id")
  private Long id;

  @Column(name = "Email", length = 64)
  private String email;

  @Column(name = "Password", length = 64)
  private String password;

  @Column(name = "Fullname", length = 128)
  private String fullname;

  @Column(name = "RegistrationDate", columnDefinition = "TIMESTAMP")
  private LocalDateTime registrationDate;

  @Column(name = "LastActivityDate", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastActivityDate;

  @Column(name = "Specialization")
  private String specialization;

  @ManyToMany
  @JoinTable(
    name = "ROLE_APP_USER",
    joinColumns = @JoinColumn(name = "App_User_Id"),
    inverseJoinColumns = @JoinColumn(name = "Role_App_Id")
  )
  private Set<AppRole> appRoleSet = new HashSet<>();
  
  @OneToMany(mappedBy = "necrotomist")
  private Set<CremationEntry> cremationEntrySet = new HashSet<>();

  public void addAppRole(AppRole appRole)
  {
    appRoleSet.add(appRole);
    appRole.getUsersSet().add(this);
  }

  public void addCremationEntry(CremationEntry cremationEntry)
  {
    cremationEntrySet.add(cremationEntry);
    cremationEntry.setNecrotomist(this);
  }

  @Override
  public int hashCode()
  {
    int hash = 5;
    hash = 41 * hash + Objects.hashCode(this.id);
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

    final User other = (User) obj;
    return Objects.equals(this.id, other.id);
  }
}
