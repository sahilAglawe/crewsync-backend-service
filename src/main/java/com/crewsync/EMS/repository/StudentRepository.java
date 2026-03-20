package com.crewsync.EMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crewsync.EMS.entity.Counsellor;
import com.crewsync.EMS.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCounsellor(Counsellor counsellor);
}
