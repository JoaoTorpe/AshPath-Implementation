package com.pdsc.ashpath.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdsc.ashpath.domain.entity.Deceased;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeceasedRepository extends JpaRepository<Deceased, Long> {

    @Query("SELECT d FROM Deceased d WHERE d.cremationEntry.id = :cremationID")
    List<Deceased> findAllDeceasedByQueueId(@Param("cremationID") Long cremationId);

}
