package com.pdsc.ashpath.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pdsc.ashpath.domain.entity.CremationEntry;

public interface CremationEntryRepository extends JpaRepository<CremationEntry, Long>
{
  @Query("SELECT ce FROM CremationEntry ce")
  @Override
  List<CremationEntry> findAll();
}
