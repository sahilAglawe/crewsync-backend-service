package com.crewsync.EMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByEmail(String email);
}
