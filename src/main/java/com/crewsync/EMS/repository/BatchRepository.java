package com.crewsync.EMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    List<Batch> findByTrainerId(Long trainerId);

    List<Batch> findByAnalystId(Long analystId);
}
