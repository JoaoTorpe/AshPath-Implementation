package com.pdsc.ashpath.dto.request;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class createDeceasedRequest {

    private String fullname;
    private String causeOfDeath;
    private LocalDate birthDate;
    private LocalDate deathDate;
}
