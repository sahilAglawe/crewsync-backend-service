package com.crewsync.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.BatchProgress;

public interface BatchProgressRepository extends JpaRepository<BatchProgress, Long> {

}
