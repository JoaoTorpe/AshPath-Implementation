package com.pdsc.ashpath.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CreateDeceasedRequest
{
  private String fullname;
  private LocalDate birthDate;
  private LocalDate deathDate;
  private String causeOfDeath;
  private String fatherName;
  private String motherName;
}
