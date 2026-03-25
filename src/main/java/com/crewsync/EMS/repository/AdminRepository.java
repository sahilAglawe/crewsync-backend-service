package com.crewsync.EMS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.crewsync.EMS.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
