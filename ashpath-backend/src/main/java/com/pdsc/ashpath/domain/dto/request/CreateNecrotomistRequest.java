package com.pdsc.ashpath.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateNecrotomistRequest
{
  private String email;
  private String password;
  private String fullname;
  private String specialization;
}
