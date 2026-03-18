package com.crewsync.EMS.service;

import java.util.List;

import com.crewsync.EMS.dto.StudentDTO;

public interface StudentService {

	StudentDTO createStudent(StudentDTO studentDTO);

    List<StudentDTO> getAllStudents();

    StudentDTO getStudentById(Long id);

    void deleteStudent(Long id);

    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
}
