package com.pdsc.ashpath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath.domain.entity.Deceased;

public interface DeceasedRepository extends JpaRepository<Deceased, Long> {
}
