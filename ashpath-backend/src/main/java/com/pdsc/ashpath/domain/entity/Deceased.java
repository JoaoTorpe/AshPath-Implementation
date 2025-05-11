package com.pdsc.ashpath.domain.entity;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DECEASED")
@NoArgsConstructor
@Getter
@Setter
public class Deceased {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULLNAME", length = 100, nullable = false)
    private String fullname;

    @Column(name = "CAUSE_OF_DEATH", length = 255, nullable = false)
    private String causeOfDeath;

    @Column(name = "BIRTH_DATE", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "DEATH_DATE", columnDefinition = "DATE")
    private LocalDate deathDate;

    public Deceased(
        String fullname,
        String causeOfDeath,
        LocalDate birthDate,
        LocalDate deathDate
    ) {
        setFullname(fullname);
        setCauseOfDeath(causeOfDeath);
        setBirthDate(birthDate);
        setDeathDate(deathDate);
    }

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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

        final Deceased other =(Deceased) obj;
        return Objects.equals(this.id, other.id);
    }
}
