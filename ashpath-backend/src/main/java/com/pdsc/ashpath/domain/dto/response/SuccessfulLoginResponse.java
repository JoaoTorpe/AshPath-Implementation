package com.pdsc.ashpath.domain.dto.response;

import com.pdsc.ashpath.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SuccessfulLoginResponse
{
  private Long loggedUserId;

  public SuccessfulLoginResponse(User user)
  {
    setLoggedUserId(user.getId());
  }
}
