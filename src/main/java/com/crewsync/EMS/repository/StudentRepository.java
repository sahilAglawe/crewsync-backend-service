package com.crewsync.EMS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
