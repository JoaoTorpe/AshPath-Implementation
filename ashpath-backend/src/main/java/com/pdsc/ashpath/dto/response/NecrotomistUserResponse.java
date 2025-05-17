package com.pdsc.ashpath.dto.response;

import java.time.LocalDateTime;

import com.pdsc.ashpath.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NecrotomistUserResponse
{
  private Long id;
  private String email;
  private String fullname;
  private LocalDateTime registrationDate;
  private LocalDateTime lastActivityDate;

  public NecrotomistUserResponse(User user)
  {
    setId(user.getId());
    setEmail(user.getEmail());
    setFullname(user.getFullname());
    setRegistrationDate(user.getRegistrationDate());
    setLastActivityDate(user.getLastActivityDate());
  }
}
