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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "APP_USER")
public class User
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotNull(message = "{NotNull.user.email}")
  @Size(min = 3, max = 254, message = "{Size.user.email}")
  @Email(message = "{Email.user.email}")
  @Column(name = "EMAIL", length = 254)
  private String email;

  @NotNull(message = "{NotNull.user.password}")
  @Size(min = 3, max = 64, message = "{Size.user.password}")
  @Pattern(
    regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()\\-+_={}\\[\\]|:;\"'<>,.?/~`]).+$",
    message = "{Pattern.user.password}"
  )
  @Column(name = "PASSWORD", length = 64)
  private String password;

  @NotNull(message = "{NotNull.user.fullname}")
  @Size(min = 3, max = 64, message = "{Size.user.fullname}")
  @Column(name = "FULL_NAME", length = 64)
  private String fullname;

  @PastOrPresent(message = "{PastOrPresent.user.registrationDate}")
  @Column(name = "REGISTRATION_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime registrationDate;

  @PastOrPresent(message = "{PastOrPresent.user.lastActivityDate}")
  @Column(name = "LAST_ACTIVITY_DATE", columnDefinition = "TIMESTAMP")
  private LocalDateTime lastActivityDate;

  @Column(name = "SPECIALIZATION", length = 64)
  private String specialization;

  @NotNull(message = "{NotNull.user.approved}")
  @Column(name = "approved")
  private Boolean approved;

  @ManyToMany
  @JoinTable(name = "ROLE_APP_USER", joinColumns = @JoinColumn(name = "APP_USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_APP_ID"))
  private Set<AppRole> appRoleSet = new HashSet<>();

  @OneToMany(mappedBy = "necrotomist")
  private Set<CremationEntry> cremationEntrySet = new HashSet<>();

  public User()
  {}

  public void setId(Long id)
  {
    this.id = id;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public String getPassword()
  {
    return this.password;
  }

  public void setFullname(String fullname)
  {
    this.fullname = fullname;
  }

  public String getFullname()
  {
    return this.fullname;
  }

  public void setRegistrationDate(LocalDateTime registrationDate)
  {
    this.registrationDate = registrationDate;
  }

  public LocalDateTime getRegistrationDate()
  {
    return this.registrationDate;
  }

  public void setLastActivityDate(LocalDateTime lastActivityDate)
  {
    this.lastActivityDate = lastActivityDate;
  }

  public LocalDateTime getLastActivityDate()
  {
    return this.lastActivityDate;
  }

  public void setSpecialization(String specialization)
  {
    this.specialization = specialization;
  }

  public String getSpecialization()
  {
    return this.specialization;
  }

  public void setAppRoleSet(Set<AppRole> appRoleSet)
  {
    this.appRoleSet = appRoleSet;
  }

  public Set<AppRole> getAppRoleSet()
  {
    return this.appRoleSet;
  }

  public void setCremationEntrySet(Set<CremationEntry> cremationEntrySet)
  {
    this.cremationEntrySet = cremationEntrySet;
  }

  public Set<CremationEntry> getCremationEntrySet()
  {
    return this.cremationEntrySet;
  }

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
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;

    final User other = (User) obj;
    return Objects.equals(this.id, other.id);
  }

  public Boolean getApproved()
  {
    return approved;
  }

  public void setApproved(Boolean approved)
  {
    this.approved = approved;
  }
}
