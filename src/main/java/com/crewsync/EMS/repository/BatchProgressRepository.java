package com.crewsync.EMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.BatchProgress;

public interface BatchProgressRepository extends JpaRepository<BatchProgress, Long> {

    List<BatchProgress> findByTrainerId(Long trainerId);
}
