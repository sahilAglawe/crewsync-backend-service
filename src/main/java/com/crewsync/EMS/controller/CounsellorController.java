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
@RequestMapping("/api/counsellors")
@RequiredArgsConstructor
public class CounsellorController {


	 private final StudentService studentService;
	 
	 @PostMapping("/students")
	    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO) {
	        return studentService.createStudent(studentDTO);
	    }

	    @GetMapping("/students")
	    public List<StudentDTO> getAllStudents() {
	        return studentService.getAllStudents();
	    }

	    @GetMapping("/students/{id}")
	    public StudentDTO getStudent(@PathVariable Long id) {
	        return studentService.getStudentById(id);
	    }

	    @DeleteMapping("/students/{id}")
	    public String deleteStudent(@PathVariable Long id) {
	        studentService.deleteStudent(id);
	        return "Student deleted successfully";
	    }
}
