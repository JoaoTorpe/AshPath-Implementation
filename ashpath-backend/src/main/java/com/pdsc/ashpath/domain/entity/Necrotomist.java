package com.pdsc.ashpath.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NECROTOMIST")
@AllArgsConstructor
@NoArgsConstructor
public class Necrotomist extends User {

    @Column(name = "NECROTOMIST_SPECIALIZATION", nullable = false)
    private String specialization;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
