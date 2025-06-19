package com.pdsc.ashpath.domain.dto.response.user;

import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.User;

public final class NecrotomistUserResponse extends AbstractUserReponse {
  protected String specialization;

  public NecrotomistUserResponse() {
  }

  public NecrotomistUserResponse(Long id, String email, String fullname, LocalDateTime registrationDate,
      LocalDateTime lastActivityDate, String specialization) {
    this.id = id;
    this.email = email;
    this.fullname = fullname;
    this.registrationDate = registrationDate;
    this.lastActivityDate = lastActivityDate;
    this.specialization = specialization;
  }

  public NecrotomistUserResponse(User user) {
    setId(user.getId());
    setEmail(user.getEmail());
    setFullname(user.getFullname());
    setRegistrationDate(user.getRegistrationDate());
    setLastActivityDate(user.getLastActivityDate());
    setSpecialization(user.getSpecialization());
  }

  public String getSpecialization() {
    return specialization;
  }

  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }
}
