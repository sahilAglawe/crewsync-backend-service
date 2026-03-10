package com.crewsync.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long> {

}
