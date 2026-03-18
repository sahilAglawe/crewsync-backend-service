package com.crewsync.EMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Counsellor;

public interface CounsellorRepository extends JpaRepository<Counsellor, Long> {
    Optional<Counsellor> findByEmail(String email);
}
