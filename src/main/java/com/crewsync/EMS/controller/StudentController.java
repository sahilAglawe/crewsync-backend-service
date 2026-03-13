package com.crewsync.EMS.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crewsync.EMS.dto.StudentDTO;
import com.crewsync.EMS.service.StudentService;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	@PostMapping
	public StudentDTO createStudent(@RequestBody StudentDTO studentDTO) {
		return studentService.createStudent(studentDTO);
	}
	
	@GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }
	
	@GetMapping("/{id}")
	public StudentDTO getStudentById(@PathVariable Long id) {
		return studentService.getStudentById(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Long id) {
	  studentService.deleteStudent(id);
}
}
