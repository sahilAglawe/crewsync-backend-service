package com.crewsync.EMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Analyst;

public interface AnalystRepository extends JpaRepository<Analyst, Long> {
    Optional<Analyst> findByEmail(String email);
}
