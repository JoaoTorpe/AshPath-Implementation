package com.pdsc.ashpath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath.domain.entity.Grave;

public interface GraveRepository extends JpaRepository<Grave, Long>
{}
